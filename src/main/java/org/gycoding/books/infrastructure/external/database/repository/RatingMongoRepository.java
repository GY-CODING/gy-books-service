package org.gycoding.books.infrastructure.external.database.repository;

import org.gycoding.books.domain.repository.RatingRepository;
import org.gycoding.books.infrastructure.external.database.model.BookAggregationEntity;
import org.gycoding.books.infrastructure.external.database.model.RatingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingMongoRepository extends MongoRepository<RatingEntity, String> {
    Optional<RatingEntity> findByBookId(String bookId);
}