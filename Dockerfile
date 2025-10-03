# Use a base picture with Java and Maven installed
FROM eclipse-temurin:23-jdk AS build

RUN apt-get update && apt-get install -y maven

# Set the working directory
WORKDIR /app

ARG SPRING_PROFILE
ARG SPRING_PORT
ARG ALLOWED_API_KEY
ARG LOGS_URL
ARG LOGS_AUTH_TOKEN
ARG GITHUB_USERNAME
ARG GITHUB_TOKEN

ENV SPRING_PROFILE=${SPRING_PROFILE}
ENV SPRING_PORT=${SPRING_PORT}
ENV ALLOWED_API_KEY=${ALLOWED_API_KEY}
ENV LOGS_URL=${LOGS_URL}
ENV LOGS_AUTH_TOKEN=${LOGS_AUTH_TOKEN}
ENV GITHUB_USERNAME=${GITHUB_USERNAME}
ENV GITHUB_TOKEN=${GITHUB_TOKEN}

# Copy the settings.xml file to the Maven configuration directory
COPY src/main/resources/settings.xml /root/.m2/settings.xml

# Copy the pom.xml file to the working directory
COPY pom.xml .

# Download the project dependencies
RUN mvn dependency:go-offline -B -s /root/.m2/settings.xml

# Copy the source code to the working directory
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Use a lightweight base picture with Java installed
FROM eclipse-temurin:23-jdk

# Set the working directory
WORKDIR /app

ARG SPRING_PROFILE
ARG SPRING_PORT
ARG ALLOWED_API_KEY
ARG LOGS_URL
ARG LOGS_AUTH_TOKEN
ARG GITHUB_USERNAME
ARG GITHUB_TOKEN

ENV SPRING_PROFILE=${SPRING_PROFILE}
ENV SPRING_PORT=${SPRING_PORT}
ENV ALLOWED_API_KEY=${ALLOWED_API_KEY}
ENV LOGS_URL=${LOGS_URL}
ENV LOGS_AUTH_TOKEN=${LOGS_AUTH_TOKEN}
ENV GITHUB_USERNAME=${GITHUB_USERNAME}
ENV GITHUB_TOKEN=${GITHUB_TOKEN}

# Copy the settings.xml file to the Maven configuration directory
COPY src/main/resources/settings.xml /root/.m2/settings.xml

# Copy the JAR file from the build stage to the current directory
COPY --from=build /app/target/*.jar ./books.jar

# Ensure the JAR file has the correct permissions
RUN chmod 777 /app/books.jar

# Expose the port on which the application will run
EXPOSE 8001

# Set the command to run the application
CMD ["java", "-jar", "books.jar"]
