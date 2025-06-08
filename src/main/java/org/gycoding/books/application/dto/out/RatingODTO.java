package org.gycoding.books.application.dto.out;

import lombok.Builder;

import java.util.Date;

@Builder
public record RatingODTO(
        String id,
        String bookId,
        Number rating,
        Date startDate,
        Date endDate
) { }