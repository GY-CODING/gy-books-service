package org.gycoding.books.application.mapper;

import org.gycoding.books.application.dto.out.BookODTO;
import org.gycoding.books.infrastructure.external.googlebooks.model.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookServiceMapper {
    BookODTO toODTO(BookEntity book);
}
