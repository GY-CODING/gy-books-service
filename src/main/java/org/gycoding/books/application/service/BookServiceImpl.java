package org.gycoding.books.application.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gycoding.books.application.dto.in.BookIDTO;
import org.gycoding.books.application.dto.in.RatingIDTO;
import org.gycoding.books.application.dto.out.BookODTO;
import org.gycoding.books.application.dto.out.RatingODTO;
import org.gycoding.books.application.mapper.BookServiceMapper;
import org.gycoding.books.application.mapper.RatingServiceMapper;
import org.gycoding.books.domain.exceptions.BooksAPIError;
import org.gycoding.books.domain.model.BookMO;
import org.gycoding.books.domain.model.BookStatus;
import org.gycoding.books.domain.model.RatingMO;
import org.gycoding.books.domain.repository.BookRepository;
import org.gycoding.books.domain.repository.RatingRepository;
import org.gycoding.exceptions.model.APIException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository repository;
    private final BookServiceMapper mapper;

    @Override
    public BookODTO getBook(String id) throws APIException {
        return repository.get(id)
                .map(mapper::toODTO)
                .orElseThrow(
                    () -> new APIException(
                            BooksAPIError.RESOURCE_NOT_FOUND.getCode(),
                            BooksAPIError.RESOURCE_NOT_FOUND.getMessage(),
                            BooksAPIError.RESOURCE_NOT_FOUND.getStatus()
                    )
                );
    }

    @Override
    public BookODTO updateBookStatus(String id, BookStatus status) throws APIException {
        final var book = repository.get(id)
                .orElse(
                    BookMO.builder()
                        .id(id)
                        .averageRating(0.0)
                        .status(status)
                        .build()
                );

        try {
            return mapper.toODTO(repository.save(book));
        } catch (IllegalArgumentException e) {
            throw new APIException(
                    BooksAPIError.CONFLICT.getCode(),
                    BooksAPIError.CONFLICT.getMessage(),
                    BooksAPIError.CONFLICT.getStatus()
            );
        }
    }

    @Override
    public void refreshAverageRating(RatingMO rating) throws APIException {
        final var persistedBook = repository.get(rating.bookId())
                .orElseGet(() -> {
                    final var defaultBook = BookMO.builder()
                            .id(rating.bookId())
                            .averageRating(rating.rating())
                            .status(BookStatus.READ)
                            .build();

                    try {
                        return repository.save(defaultBook);
                    } catch (APIException e) {
                        throw new RuntimeException(e);
                    }
                });

        repository.save(persistedBook);
    }
}
