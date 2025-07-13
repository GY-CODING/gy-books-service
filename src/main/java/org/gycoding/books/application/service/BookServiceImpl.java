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
import org.gycoding.books.domain.model.UserDataMO;
import org.gycoding.books.domain.repository.BookRepository;
import org.gycoding.books.domain.repository.GYAccountsFacade;
import org.gycoding.exceptions.model.APIException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public BookODTO getBook(String id, UUID profileId) throws APIException {
        return repository.get(id, profileId)
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
    public List<BookODTO> listBooks(UUID profileId, Pageable pageable) throws APIException {
        return repository.listByProfileId(profileId, pageable).stream()
                .map(mapper::toODTO)
                .toList();
    }

    @Override
    public BookODTO updateBook(BookIDTO book) throws APIException {
        try {
            final var updatedBook = repository.update(mapper.toMO(book));

            if(book.userData().status().equals(BookStatus.READ)) {
                refreshAverageRating(updatedBook);
            }

            return mapper.toODTO(updatedBook);
        } catch (Exception e) {
            throw new APIException(
                    BooksAPIError.CONFLICT.getCode(),
                    BooksAPIError.CONFLICT.getMessage(),
                    BooksAPIError.CONFLICT.getStatus()
            );
        }
    }

    @Override
    public void removeBook(String id, UUID profileId) throws APIException {
        try {
            repository.remove(id, profileId);
        } catch (Exception e) {
            throw new APIException(
                    BooksAPIError.RESOURCE_NOT_FOUND.getCode(),
                    BooksAPIError.RESOURCE_NOT_FOUND.getMessage(),
                    BooksAPIError.RESOURCE_NOT_FOUND.getStatus()
            );
        }
    }

    private void refreshAverageRating(BookMO book) throws APIException {
        final var ratings = new ArrayList<Double>(repository.list(book.id())
                .stream()
                .map(ratingElement -> ratingElement.userData().rating().doubleValue())
                .toList());

        ratings.add(book.userData().rating().doubleValue());

        final var averageRating = ratings.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);

        repository.update(
                BookMO.builder()
                    .id(book.id())
                    .averageRating(averageRating)
                    .userData(
                            UserDataMO.builder()
                                    .profileId(book.userData().profileId())
                                    .build()
                    )
                    .build()
        );
    }
}
