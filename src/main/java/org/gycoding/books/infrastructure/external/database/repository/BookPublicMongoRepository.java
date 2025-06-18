package org.gycoding.books.infrastructure.external.database.repository;

import org.gycoding.books.infrastructure.external.database.model.BookPublicEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookPublicMongoRepository extends MongoRepository<BookPublicEntity, String> {
    @Query("{ 'id' : ?0 }")
    Optional<BookPublicEntity> findById(String id);
}