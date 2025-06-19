package org.gycoding.books.infrastructure.external.database.mapper;

import org.gycoding.books.application.dto.in.UserDataIDTO;
import org.gycoding.books.application.dto.out.UserDataODTO;
import org.gycoding.books.domain.model.BookMO;
import org.gycoding.books.domain.model.UserDataMO;
import org.gycoding.books.infrastructure.api.dto.in.UserDataRQDTO;
import org.gycoding.books.infrastructure.api.dto.out.UserDataRSDTO;
import org.gycoding.books.infrastructure.external.database.model.BookEntity;
import org.gycoding.books.infrastructure.external.database.model.BookPublicEntity;
import org.gycoding.books.infrastructure.external.database.model.UserDataEntity;
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

    BookEntity toEntity(BookMO book);

    UserDataMO toMO(UserDataEntity userData);

    UserDataEntity toEntity(UserDataMO userData);

    @Mapping(target = "mongoId", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BookPublicEntity toUpdatedPublicEntity(@MappingTarget BookPublicEntity persistedBookPublic, BookMO book);

    @Mapping(target = "mongoId", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BookEntity toUpdatedEntity(@MappingTarget BookEntity persistedBook, BookMO book);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserDataEntity toUpdatedEntity(@MappingTarget UserDataEntity persistedUserData, UserDataMO userData);

    default String formatDate(LocalDate date) {
        if(date == null) return null;

        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
