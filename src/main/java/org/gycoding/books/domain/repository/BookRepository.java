package org.gycoding.books.domain.repository;

import org.gycoding.books.domain.model.BookMO;
import org.gycoding.exceptions.model.APIException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository {
    BookMO save(BookMO book, String userId);
    BookMO update(BookMO book, String userId);
    Optional<BookMO> get(String id);
    Optional<BookMO> get(String id, String userId);
}
