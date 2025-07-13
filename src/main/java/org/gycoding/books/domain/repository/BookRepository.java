package org.gycoding.books.domain.repository;

import org.gycoding.books.domain.model.BookMO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository {
    Optional<BookMO> get(String id);
    Optional<BookMO> get(String id, UUID profileId);
    List<BookMO> list(String id);
    List<BookMO> listByProfileId(UUID profileId, Pageable pageable);
    BookMO save(BookMO book);
    BookMO update(BookMO book);
}
