package org.gycoding.books.infrastructure.external.database.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.gycoding.books.domain.model.BookStatus;
import org.springframework.data.annotation.Id;

@Builder
@Getter
@Setter
public class UserDataEntity {
    private String profileId;
    private String editionId;
    private BookStatus status;
    private Number rating;
    private String review;
    private Number progress;
    private String startDate;
    private String endDate;
}