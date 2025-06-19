package org.gycoding.books.domain.model;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
public record BookMO(
        String id,
        Number averageRating,
        UserDataMO userData
) { }