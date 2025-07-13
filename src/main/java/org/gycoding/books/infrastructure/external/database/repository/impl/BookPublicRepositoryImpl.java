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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookPublicRepositoryImpl implements BookRepository {
    private final BookPublicMongoRepository publicRepository;
    private final BookMongoRepository repository;
    private final BookRepositoryMapper mapper;

    @Override
    public Optional<BookMO> get(String id) {
        return publicRepository.findById(id)
                .map(mapper::toPublicMO);
    }

    @Override
    public Optional<BookMO> get(String id, UUID profileId) {
        final var bookPublic = publicRepository.findById(id);
        final var book = repository.findByIdAndProfileId(id, profileId.toString());

        if(bookPublic.isEmpty() || book.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(mapper.toMO(bookPublic.get(), book.get()));
    }

    @Override
    public List<BookMO> list(String id) {
        final var booksPublic = publicRepository.findAllById(id);
        final var books = repository.findAllById(id);

        return booksPublic.stream()
                .map(publicEntity -> {
                    var bookEntity = books.stream()
                            .filter(book -> book.getId().equals(publicEntity.getId()))
                            .findFirst()
                            .orElse(null);
                    return mapper.toMO(publicEntity, bookEntity);
                })
                .toList();
    }

    @Override
    public List<BookMO> listByProfileId(UUID profileId, Pageable pageable) {
        final var books = repository.findAllByUserDataProfileId(profileId.toString(), pageable);

        return books.stream()
                .map(book -> {
                    final var booksPublic = publicRepository.findAllById(book.getId());

                    var persistedBookPublic = booksPublic.stream()
                            .filter(bookPublic -> book.getId().equals(bookPublic.getId()))
                            .findFirst()
                            .orElse(null);

                    return mapper.toMO(persistedBookPublic, book);
                })
                .toList();
    }

    @Override
    public BookMO save(BookMO book) {
        var savedBookPublic = publicRepository.findById(book.id())
                .orElse(
                    publicRepository.save(mapper.toPublicEntity(book))
                );

        var savedBook = repository.findByIdAndProfileId(book.id(), book.userData().profileId().toString())
                .orElse(
                    repository.save(mapper.toEntity(book))
                );

        return mapper.toMO(savedBookPublic, savedBook);
    }

    @Override
    public BookMO update(BookMO book) {
        final var persistedBookPublic = publicRepository.findById(book.id());
        final var persistedBook = repository.findByIdAndProfileId(book.id(), book.userData().profileId().toString());

        if(persistedBookPublic.isEmpty() && persistedBook.isEmpty()) {
            return save(book);
        } else if(persistedBookPublic.isEmpty()) {
            final var savedBookPublic = publicRepository.save(mapper.toPublicEntity(book));

            return mapper.toMO(savedBookPublic, persistedBook.get());
        } else if(persistedBook.isEmpty()) {
            final var savedBook = repository.save(mapper.toEntity(book));

            return mapper.toMO(persistedBookPublic.get(), savedBook);
        }

        final var updatedBookPublic = publicRepository.save(mapper.toUpdatedPublicEntity(persistedBookPublic.get(), book));
        final var updatedBook = repository.save(mapper.toUpdatedEntity(persistedBook.get(), book));

        return mapper.toMO(updatedBookPublic, updatedBook);
    }

    @Override
    public void remove(String id, UUID profileId) throws APIException {
        final var persistedBook = repository.findByIdAndProfileId(id, profileId.toString())
                .orElseThrow(() -> new APIException(
                        BooksAPIError.RESOURCE_NOT_FOUND.getCode(),
                        BooksAPIError.RESOURCE_NOT_FOUND.getMessage(),
                        BooksAPIError.RESOURCE_NOT_FOUND.getStatus()
                ));

        repository.delete(persistedBook);
    }
}
