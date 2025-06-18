package org.gycoding.books.domain.repository;

import org.gycoding.books.domain.model.BookMO;
import org.gycoding.books.domain.model.RatingMO;
import org.gycoding.exceptions.model.APIException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository {
    BookMO save(BookMO book) throws APIException;
    BookMO update(BookMO book) throws APIException;
    Optional<BookMO> get(String id);
}
