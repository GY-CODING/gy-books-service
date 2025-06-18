package org.gycoding.books.application.dto.out;

import lombok.Builder;
import org.gycoding.books.domain.model.BookStatus;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
public record BookODTO(
        String id,
        Number averageRating,
        BookStatus status
) { }