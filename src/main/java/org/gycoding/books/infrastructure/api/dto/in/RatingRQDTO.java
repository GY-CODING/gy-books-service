package org.gycoding.books.infrastructure.api.dto.in;

import lombok.Builder;

import java.util.Date;

@Builder
public record RatingRQDTO(
        String bookId,
        Number rating,
        String startDate,
        String endDate
) { }