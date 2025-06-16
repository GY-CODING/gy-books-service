package org.gycoding.books.infrastructure.external.database.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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