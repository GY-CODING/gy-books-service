package org.gycoding.books.infrastructure.external.database.mapper;

import org.gycoding.books.domain.model.RatingMO;
import org.gycoding.books.infrastructure.external.database.model.RatingEntity;
import org.mapstruct.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = { UUID.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RatingRepositoryMapper {
    @Mapping(target = "id", expression = "java(UUID.fromString(rating.getId()))")
    RatingMO toMO(RatingEntity rating);

    @Mapping(target = "id", expression = "java(rating.id().toString())")
    RatingEntity toEntity(RatingMO rating);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mongoId", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RatingEntity toUpdatedEntity(@MappingTarget RatingEntity persistedRating, RatingMO rating);

    default String formatDate(LocalDate date) {
        if(date == null) return null;

        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
