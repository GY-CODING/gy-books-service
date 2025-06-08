package org.gycoding.books.application.dto.out;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
public record BookODTO(
        UUID id,
        String title,
        String description,
        String cover,
        List<AuthorODTO> authors,
        SeriesODTO series,
        Number averageRating
) { }