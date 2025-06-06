package org.gycoding.books.infrastructure.external.database.repository;

import org.gycoding.books.infrastructure.external.database.model.FooEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooMongoRepository extends MongoRepository<FooEntity, String> { }
