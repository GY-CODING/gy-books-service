package org.gycoding.books.infrastructure.external.database.repository.impl;

import lombok.AllArgsConstructor;
import org.gycoding.books.domain.exceptions.BooksAPIError;
import org.gycoding.books.domain.model.BookMO;
import org.gycoding.books.domain.model.RatingMO;
import org.gycoding.books.domain.repository.BookRepository;
import org.gycoding.books.domain.repository.RatingRepository;
import org.gycoding.books.infrastructure.external.database.mapper.BookRepositoryMapper;
import org.gycoding.books.infrastructure.external.database.mapper.RatingRepositoryMapper;
import org.gycoding.books.infrastructure.external.database.repository.BookMongoRepository;
import org.gycoding.books.infrastructure.external.database.repository.RatingMongoRepository;
import org.gycoding.exceptions.model.APIException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final BookMongoRepository repository;
    private final BookRepositoryMapper mapper;

    @Override
    public BookMO save(BookMO book) throws APIException {
        if(repository.findById(book.id()).isPresent()) {
            throw new APIException(
                    BooksAPIError.CONFLICT.getCode(),
                    BooksAPIError.CONFLICT.getMessage(),
                    BooksAPIError.CONFLICT.getStatus()
            );
        }

        return mapper.toMO(repository.save(mapper.toEntity(book)));
    }

    @Override
    public Optional<BookMO> get(String id) {
        return repository.findById(id)
                .map(mapper::toMO);
    }
}
