package org.gycoding.books.infrastructure.external.database.repository.impl;

import lombok.AllArgsConstructor;
import org.gycoding.books.domain.exceptions.BooksAPIError;
import org.gycoding.books.domain.model.BookMO;
import org.gycoding.books.domain.repository.BookRepository;
import org.gycoding.books.infrastructure.external.database.mapper.BookRepositoryMapper;
import org.gycoding.books.infrastructure.external.database.model.BookEntity;
import org.gycoding.books.infrastructure.external.database.model.BookPublicEntity;
import org.gycoding.books.infrastructure.external.database.repository.BookMongoRepository;
import org.gycoding.books.infrastructure.external.database.repository.BookPublicMongoRepository;
import org.gycoding.exceptions.model.APIException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BookPublicRepositoryImpl implements BookRepository {
    private final BookPublicMongoRepository publicRepository;
    private final BookMongoRepository repository;
    private final BookRepositoryMapper mapper;

    @Override
    public BookMO save(BookMO book, String userId) {
        var savedBookPublic = publicRepository.findById(book.id())
                .orElse(
                    publicRepository.save(mapper.toPublicEntity(book))
                );

        var savedBook = repository.findById(book.id())
                .orElse(
                    repository.save(mapper.toEntity(book, userId))
                );

        return mapper.toMO(savedBookPublic, savedBook);
    }

    @Override
    public BookMO update(BookMO book, String userId) {
        final var persistedBookPublic = publicRepository.findById(book.id());
        final var persistedBook = repository.findByIdAndUserId(book.id(), userId);

        if(persistedBookPublic.isEmpty() || persistedBook.isEmpty()) {
            return save(book, userId);
        }

        final var updatedBookPublic = publicRepository.save(mapper.toUpdatedPublicEntity(persistedBookPublic.get(), book));
        final var updatedBook = repository.save(mapper.toUpdatedEntity(persistedBook.get(), book));

        return mapper.toMO(updatedBookPublic, updatedBook);
    }

    @Override
    public Optional<BookMO> get(String id) {
        return publicRepository.findById(id)
                .map(mapper::toPublicMO);
    }

    @Override
    public Optional<BookMO> get(String id, String userId) {
        final var bookPublic = publicRepository.findById(id);
        final var book = repository.findByIdAndUserId(id, userId);

        if(bookPublic.isEmpty() || book.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(mapper.toMO(bookPublic.get(), book.get()));
    }
}
