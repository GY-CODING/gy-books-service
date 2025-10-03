package org.gycoding.books.domain.model;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserDataMO(
        UUID profileId,
        String editionId,
        BookStatus status,
        Number rating,
        String review,
        Number progress,
        String startDate,
        String endDate
) { }