package org.gycoding.books.infrastructure.api.dto.in;

import lombok.Builder;
import org.gycoding.books.domain.model.BookStatus;

@Builder
public record UserDataRQDTO(
        String editionId,
        BookStatus status,
        Number rating,
        String review,
        Number progress,
        String startDate,
        String endDate
) { }