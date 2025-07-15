package org.gycoding.books.application.dto.in;

import lombok.Builder;
import org.gycoding.books.domain.model.BookStatus;

import java.util.UUID;

@Builder
public record UserDataIDTO(
        UUID profileId,
        BookStatus status,
        Number rating,
        Number progress,
        String startDate,
        String endDate
) { }