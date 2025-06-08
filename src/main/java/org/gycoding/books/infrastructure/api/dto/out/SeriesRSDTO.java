package org.gycoding.books.infrastructure.api.dto.out;

import lombok.Builder;

@Builder
public record SeriesRSDTO(
        String id,
        String name,
        String selfLink
) { }