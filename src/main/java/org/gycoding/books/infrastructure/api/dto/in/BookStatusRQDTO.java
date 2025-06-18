package org.gycoding.books.infrastructure.api.dto.in;

import org.gycoding.books.domain.model.BookStatus;

public record BookStatusRQDTO(
        BookStatus status
) { }