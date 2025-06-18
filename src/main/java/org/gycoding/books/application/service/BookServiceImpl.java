package org.gycoding.books.application.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gycoding.books.application.dto.in.BookIDTO;
import org.gycoding.books.application.dto.out.BookODTO;
import org.gycoding.books.application.mapper.BookServiceMapper;
import org.gycoding.books.domain.exceptions.BooksAPIError;
import org.gycoding.books.domain.model.BookMO;
import org.gycoding.books.domain.model.BookStatus;
import org.gycoding.books.domain.model.RatingMO;
import org.gycoding.books.domain.repository.BookRepository;
import org.gycoding.exceptions.model.APIException;
import org.springframework.stereotype.Service;

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
    public BookODTO getBook(String id, String userId) throws APIException {
        return repository.get(id, userId)
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
    public BookODTO updateBook(BookIDTO book, String userId) throws APIException {
        try {
            return mapper.toODTO(repository.update(mapper.toMO(book), userId));
        } catch (Exception e) {
            throw new APIException(
                    BooksAPIError.CONFLICT.getCode(),
                    BooksAPIError.CONFLICT.getMessage(),
                    BooksAPIError.CONFLICT.getStatus()
            );
        }
    }

    @Override
    public void refreshAverageRating(String bookId, Number rating, String userId) throws APIException {
        final var book = BookMO.builder()
                .id(bookId)
                .averageRating(rating)
                .status(BookStatus.READ)
                .build();

        repository.update(book, userId);
    }
}
