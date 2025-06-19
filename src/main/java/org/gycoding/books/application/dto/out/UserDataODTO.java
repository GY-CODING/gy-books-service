package org.gycoding.books.application.dto.out;

import lombok.Builder;
import org.gycoding.books.domain.model.BookStatus;

@Builder
public record UserDataODTO(
        String userId,
        BookStatus status,
        Number rating,
        String startDate,
        String endDate
) { }