package org.gycoding.service.domain.repository;

import org.gycoding.service.infrastructure.external.googlebooks.model.BookEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GoogleBooksFacade {
    List<BookEntity> queryBooks(String title, String author);
}
