package org.gycoding.books.domain.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BooksAPIError {
    CONFLICT("An internal conflict between external data and the API has occurred. Requested data may already exist.", HttpStatus.CONFLICT),
    RESOURCE_NOT_FOUND("This resource was not found.", HttpStatus.NOT_FOUND),
    FORBIDDEN("The username has no permission to access this resource.", HttpStatus.FORBIDDEN),

    SERVER_ERROR("An internal server error has occurred, sorry for the inconvenience.", HttpStatus.INTERNAL_SERVER_ERROR);

    public final String code;
    public final String message;
    public final HttpStatus status;

    BooksAPIError(String message, HttpStatus status) {
        this.code       = this.name();
        this.message    = message;
        this.status     = status;
    }
}