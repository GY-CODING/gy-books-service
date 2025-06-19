package org.gycoding.books.application.dto.in;

import lombok.Builder;
import org.gycoding.books.domain.model.BookStatus;

@Builder
public record BookIDTO(
        String id,
        Number averageRating,
        UserDataIDTO userData
) { }