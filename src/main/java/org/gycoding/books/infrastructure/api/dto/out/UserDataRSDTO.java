package org.gycoding.books.infrastructure.api.dto.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import org.gycoding.books.domain.model.BookStatus;

import java.util.UUID;

@Builder
public record UserDataRSDTO(
        UUID profileId,
        BookStatus status,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Number rating,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Number progress,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String startDate,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String endDate
) { }