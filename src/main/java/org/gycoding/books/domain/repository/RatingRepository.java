package org.gycoding.books.domain.repository;

import org.gycoding.books.domain.model.RatingMO;
import org.gycoding.books.infrastructure.external.database.model.BookAggregationEntity;
import org.gycoding.books.infrastructure.external.database.model.RatingEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RatingRepository {
    RatingEntity save(RatingMO rating);
    Optional<RatingEntity> get(UUID bookId);
}
