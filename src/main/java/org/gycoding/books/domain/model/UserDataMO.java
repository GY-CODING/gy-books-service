package org.gycoding.books.domain.model;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserDataMO(
        UUID profileId,
        BookStatus status,
        Number rating,
        String startDate,
        String endDate
) { }