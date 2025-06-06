package org.gycoding.service.infrastructure.api.mapper;

import org.gycoding.service.application.dto.out.BookODTO;
import org.gycoding.service.infrastructure.api.dto.out.BookRSDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookControllerMapper {

    BookRSDTO toRSDTO(BookODTO book);
}
