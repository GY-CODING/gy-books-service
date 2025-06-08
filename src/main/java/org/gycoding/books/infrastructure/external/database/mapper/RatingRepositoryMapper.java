package org.gycoding.books.infrastructure.external.database.mapper;

import org.gycoding.books.domain.model.RatingMO;
import org.gycoding.books.infrastructure.external.database.model.RatingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RatingRepositoryMapper {
    RatingEntity toEntity(RatingMO rating);
}
