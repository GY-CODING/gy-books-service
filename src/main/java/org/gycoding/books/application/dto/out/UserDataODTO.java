package org.gycoding.books.application.dto.out;

import lombok.Builder;
import org.gycoding.books.domain.model.BookStatus;

import java.util.UUID;

@Builder
public record UserDataODTO(
        UUID profileId,
        BookStatus status,
        Number rating,
        Number progress,
        String startDate,
        String endDate
) { }