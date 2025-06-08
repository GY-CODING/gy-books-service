package org.gycoding.books.infrastructure.external.database.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.gycoding.books.domain.model.AuthorMO;
import org.gycoding.books.domain.model.SeriesMO;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
public class BookEntity {
    private UUID id;
    private String title;
    private String description;
    private String cover;
    private List<AuthorMO> authors;
    private SeriesMO series;
    private Number averageRating;
}