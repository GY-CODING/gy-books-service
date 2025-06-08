package org.gycoding.books.domain.repository;

import org.gycoding.books.domain.model.BookMO;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OpenLibraryFacade {
    List<BookMO> queryBooks(String query);
}
