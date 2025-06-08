package org.gycoding.books.domain.model;

import lombok.Builder;

@Builder
public record SeriesMO(
        String id,
        String name,
        String selfLink
) { }