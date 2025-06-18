package org.gycoding.books.infrastructure.external.database.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.gycoding.books.domain.model.BookStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Setter
@Document(collection = "BookPublic")
public class BookPublicEntity {
    @Id
    private String mongoId;
    private String id;
    private Number averageRating;
}