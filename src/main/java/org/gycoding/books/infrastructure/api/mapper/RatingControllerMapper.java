package org.gycoding.books.infrastructure.api.mapper;

import org.gycoding.books.application.dto.in.RatingIDTO;
import org.gycoding.books.application.dto.out.RatingODTO;
import org.gycoding.books.infrastructure.api.dto.in.RatingRQDTO;
import org.gycoding.books.infrastructure.api.dto.out.RatingRSDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = { UUID.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RatingControllerMapper {
    RatingRSDTO toRSDTO(RatingODTO rating);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "startDate", expression = "java(toDateFormat(rating.startDate()))")
    @Mapping(target = "endDate", expression = "java(toDateFormat(rating.endDate()))")
    RatingIDTO toIDTO(RatingRQDTO rating, String userId);

    default LocalDate toDateFormat(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
