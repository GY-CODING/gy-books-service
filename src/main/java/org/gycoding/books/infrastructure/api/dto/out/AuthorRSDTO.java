package org.gycoding.books.infrastructure.api.dto.out;

import lombok.Builder;

@Builder
public record AuthorRSDTO(
        String name
) { }
