package org.gycoding.books.infrastructure.external.database.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.gycoding.books.domain.model.AuthorMO;
import org.gycoding.books.domain.model.SeriesMO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@Document(collection = "Rating")
public class RatingEntity {
    @Id
    private String mongoId;
    private String id;
    private String bookId;
    private String userId;
    private Number rating;
    private String startDate;
    private String endDate;
}