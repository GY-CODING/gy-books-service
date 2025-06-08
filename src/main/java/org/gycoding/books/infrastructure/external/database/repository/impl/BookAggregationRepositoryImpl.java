package org.gycoding.books.infrastructure.external.database.repository.impl;

import lombok.AllArgsConstructor;
import org.gycoding.books.domain.repository.BookAggregationRepository;
import org.gycoding.books.infrastructure.external.database.model.BookAggregationEntity;
import org.gycoding.books.infrastructure.external.database.repository.BookAggregationMongoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookAggregationRepositoryImpl implements BookAggregationRepository {
    private final BookAggregationMongoRepository repository;

    @Override
    public BookAggregationEntity save(String key) {
        return repository.save(
                BookAggregationEntity.builder()
                        .id(UUID.randomUUID().toString())
                        .key(key)
                        .build()
        );
    }

    @Override
    public Optional<UUID> getId(String key) {
        return repository.findByKey(key)
                .map(aggregation -> UUID.fromString(aggregation.getId()))
                .or(() -> {
                    final var aggregation = save(key);
                    return Optional.of(UUID.fromString(aggregation.getId()));
                });
    }
}
