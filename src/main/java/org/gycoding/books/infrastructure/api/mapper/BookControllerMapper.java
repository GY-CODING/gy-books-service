package org.gycoding.books.infrastructure.api.mapper;

import org.gycoding.books.application.dto.in.BookIDTO;
import org.gycoding.books.application.dto.in.UserDataIDTO;
import org.gycoding.books.application.dto.out.BookODTO;
import org.gycoding.books.application.dto.out.UserDataODTO;
import org.gycoding.books.infrastructure.api.dto.in.BookRQDTO;
import org.gycoding.books.infrastructure.api.dto.in.UserDataRQDTO;
import org.gycoding.books.infrastructure.api.dto.out.BookRSDTO;
import org.gycoding.books.infrastructure.api.dto.out.UserDataRSDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = { UUID.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookControllerMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "userData", expression = "java(toIDTO(book.userData(), userId))")
    BookIDTO toIDTO(BookRQDTO book, String id, String userId);

    BookRSDTO toRSDTO(BookODTO book);

    UserDataIDTO toIDTO(UserDataRQDTO userData, String userId);

    UserDataRSDTO toRSDTO(UserDataODTO userData);

    default LocalDate toDateFormat(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
