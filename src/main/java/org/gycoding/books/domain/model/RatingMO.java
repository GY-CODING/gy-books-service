package org.gycoding.books.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Builder
public record RatingMO(
        UUID id,
        String bookId,
        String userId,
        Number rating,
        LocalDate startDate,
        LocalDate endDate
) { }