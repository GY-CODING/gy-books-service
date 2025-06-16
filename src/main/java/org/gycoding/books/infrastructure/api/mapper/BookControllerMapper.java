package org.gycoding.books.infrastructure.api.mapper;

import org.gycoding.books.application.dto.in.BookIDTO;
import org.gycoding.books.application.dto.in.RatingIDTO;
import org.gycoding.books.application.dto.out.BookODTO;
import org.gycoding.books.application.dto.out.RatingODTO;
import org.gycoding.books.infrastructure.api.dto.in.BookRQDTO;
import org.gycoding.books.infrastructure.api.dto.in.RatingRQDTO;
import org.gycoding.books.infrastructure.api.dto.out.BookRSDTO;
import org.gycoding.books.infrastructure.api.dto.out.RatingRSDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = { UUID.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookControllerMapper {
    BookIDTO toIDTO(BookRQDTO book);
    BookRSDTO toRSDTO(BookODTO book);

    default LocalDate toDateFormat(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
