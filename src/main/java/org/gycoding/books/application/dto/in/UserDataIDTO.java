package org.gycoding.books.application.dto.in;

import lombok.Builder;
import org.gycoding.books.domain.model.BookStatus;

@Builder
public record UserDataIDTO(
        String userId,
        BookStatus status,
        Number rating,
        String startDate,
        String endDate
) { }