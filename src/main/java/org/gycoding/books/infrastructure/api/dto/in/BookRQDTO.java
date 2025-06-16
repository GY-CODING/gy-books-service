package org.gycoding.books.infrastructure.api.dto.in;

import lombok.Builder;
import org.gycoding.books.domain.model.BookStatus;

@Builder
public record BookRQDTO(
        String id,
        Number averageRating,
        BookStatus status
) { }