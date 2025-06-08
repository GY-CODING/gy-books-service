package org.gycoding.books.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Builder
public record RatingMO(
        String id,
        String bookId,
        Number rating,
        Date startDate,
        Date endDate
) { }