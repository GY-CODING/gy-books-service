package org.gycoding.service.application.mapper;

import org.gycoding.service.application.dto.out.BookODTO;
import org.gycoding.service.infrastructure.external.googlebooks.model.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookServiceMapper {
    BookODTO toODTO(BookEntity book);
}
