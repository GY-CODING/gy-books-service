package org.gycoding.books.infrastructure.api.dto.out;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Builder
public record RatingRSDTO(
        UUID id,
        String bookId,
        String userId,
        Number rating,
        LocalDate startDate,
        LocalDate endDate
) { }