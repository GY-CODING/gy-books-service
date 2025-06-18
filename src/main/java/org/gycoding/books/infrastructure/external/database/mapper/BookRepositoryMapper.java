package org.gycoding.books.infrastructure.external.database.mapper;

import org.gycoding.books.domain.model.BookMO;
import org.gycoding.books.infrastructure.external.database.model.BookEntity;
import org.gycoding.books.infrastructure.external.database.model.BookPublicEntity;
import org.mapstruct.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = { UUID.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookRepositoryMapper {
    @Mapping(target = "id", source = "bookPublic.id")
    BookMO toMO(BookPublicEntity bookPublic, BookEntity book);

    BookMO toPublicMO(BookPublicEntity bookPublic);

    BookPublicEntity toPublicEntity(BookMO book);

    @Mapping(target = "userId", source = "userId")
    BookEntity toEntity(BookMO book, String userId);

    @Mapping(target = "mongoId", ignore = true)
    @Mapping(target = "averageRating", expression = "java(checkNull(book.averageRating()))")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BookPublicEntity toUpdatedPublicEntity(@MappingTarget BookPublicEntity persistedBookPublic, BookMO book);

    @Mapping(target = "mongoId", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BookEntity toUpdatedEntity(@MappingTarget BookEntity persistedBook, BookMO book);

    default String formatDate(LocalDate date) {
        if(date == null) return null;

        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    default Number checkNull(Number number) {
        return number == null ? 0.0 : number;
    }
}
