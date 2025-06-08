package org.gycoding.books.domain.repository;

import org.gycoding.books.infrastructure.external.database.model.BookAggregationEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookAggregationRepository {
    BookAggregationEntity save(String key);
    Optional<UUID> getId(String key);
}
