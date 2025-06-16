package org.gycoding.books.infrastructure.external.database.mapper;

import org.gycoding.books.domain.model.BookMO;
import org.gycoding.books.domain.model.RatingMO;
import org.gycoding.books.infrastructure.external.database.model.BookEntity;
import org.gycoding.books.infrastructure.external.database.model.RatingEntity;
import org.mapstruct.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = { UUID.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookRepositoryMapper {
    BookMO toMO(BookEntity book);

    BookEntity toEntity(BookMO book);

    default String formatDate(LocalDate date) {
        if(date == null) return null;

        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
