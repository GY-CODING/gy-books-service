package org.gycoding.books.domain.repository;

import org.gycoding.books.domain.model.BookMO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository {
    Optional<BookMO> get(String id);
    Optional<BookMO> get(String id, String userId);
    List<BookMO> list(String id);
    List<BookMO> listByUserID(String userId);
    BookMO save(BookMO book, String userId);
    BookMO update(BookMO book);
}
