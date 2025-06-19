package org.gycoding.books.infrastructure.api.dto.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import org.gycoding.books.domain.model.BookStatus;

@Builder
public record UserDataRSDTO(
        String userId,
        BookStatus status,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Number rating,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String startDate,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String endDate
) { }