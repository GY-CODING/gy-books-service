package org.gycoding.books.domain.repository;

import org.gycoding.books.infrastructure.external.googlebooks.model.BookEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GoogleBooksFacade {
    List<BookEntity> queryBooks(String title, String author);
}
