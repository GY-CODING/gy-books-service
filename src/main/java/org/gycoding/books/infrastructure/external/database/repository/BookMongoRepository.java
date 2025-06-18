package org.gycoding.books.infrastructure.external.database.repository;

import org.gycoding.books.infrastructure.external.database.model.BookEntity;
import org.gycoding.books.infrastructure.external.database.model.RatingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookMongoRepository extends MongoRepository<BookEntity, String> {
    @Query("{ 'id' : ?0 }")
    Optional<BookEntity> findById(String id);
}