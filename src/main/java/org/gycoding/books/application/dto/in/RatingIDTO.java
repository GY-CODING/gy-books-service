package org.gycoding.books.application.dto.in;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Builder
public record RatingIDTO(
        String bookId,
        String userId,
        Number rating,
        LocalDate startDate,
        LocalDate endDate
) { }