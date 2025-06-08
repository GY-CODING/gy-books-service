package org.gycoding.books.infrastructure.api.dto.out;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
public record BookRSDTO(
        UUID id,
        String title,
        String cover,
        List<AuthorRSDTO> authors,
        SeriesRSDTO series,
        Number averageRating
) { }