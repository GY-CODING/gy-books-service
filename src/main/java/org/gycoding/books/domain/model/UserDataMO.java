package org.gycoding.books.domain.model;

import lombok.Builder;

@Builder
public record UserDataMO(
        String userId,
        BookStatus status,
        Number rating,
        String startDate,
        String endDate
) { }