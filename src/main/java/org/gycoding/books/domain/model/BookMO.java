package org.gycoding.books.domain.model;

import lombok.Builder;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
public record BookMO(
        String id,
        String title,
        String cover,
        List<String> authors,
        UUID seriesId,
        Date publishDate,
        Integer pageCount
) {
}
