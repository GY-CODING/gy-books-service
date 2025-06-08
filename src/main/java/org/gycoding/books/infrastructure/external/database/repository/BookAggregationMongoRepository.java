package org.gycoding.books.infrastructure.external.database.repository;

import org.gycoding.books.infrastructure.external.database.model.BookAggregationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookAggregationMongoRepository extends MongoRepository<BookAggregationEntity, String> {
    Optional<BookAggregationEntity> findByKey(String key);
}
