package org.gycoding.books.infrastructure.external.database.repository.impl;

import lombok.AllArgsConstructor;
import org.gycoding.books.domain.model.RatingMO;
import org.gycoding.books.domain.repository.BookAggregationRepository;
import org.gycoding.books.domain.repository.RatingRepository;
import org.gycoding.books.infrastructure.external.database.mapper.RatingRepositoryMapper;
import org.gycoding.books.infrastructure.external.database.model.BookAggregationEntity;
import org.gycoding.books.infrastructure.external.database.model.RatingEntity;
import org.gycoding.books.infrastructure.external.database.repository.BookAggregationMongoRepository;
import org.gycoding.books.infrastructure.external.database.repository.RatingMongoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RatingRepositoryImpl implements RatingRepository {
    private final RatingMongoRepository repository;
    private final RatingRepositoryMapper mapper;

    @Override
    public RatingEntity save(RatingMO rating) {
        return repository.save(mapper.toEntity(rating));
    }

    @Override
    public Optional<RatingEntity> get(UUID bookId) {
        return repository.findByBookId(bookId.toString());
    }
}
