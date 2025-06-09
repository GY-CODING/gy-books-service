package org.gycoding.books.application.mapper;

import org.gycoding.books.application.dto.in.RatingIDTO;
import org.gycoding.books.application.dto.out.RatingODTO;
import org.gycoding.books.domain.model.RatingMO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RatingServiceMapper {
    RatingODTO toODTO(RatingMO rating);
    RatingMO toMO(RatingIDTO rating);
}
