package org.gycoding.books.infrastructure.external.database.repository;

import org.gycoding.books.infrastructure.external.database.model.RatingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingMongoRepository extends MongoRepository<RatingEntity, String> {
    Optional<RatingEntity> findByBookIdAndUserId(String bookId, String userId);
    List<RatingEntity> findAllByUserId(String userId);
}