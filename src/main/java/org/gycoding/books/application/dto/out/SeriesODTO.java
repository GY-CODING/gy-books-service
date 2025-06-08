package org.gycoding.books.application.dto.out;

import lombok.Builder;

@Builder
public record SeriesODTO(
        String id,
        String name,
        String selfLink
) { }