package org.gycoding.books.application.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gycoding.books.application.dto.in.RatingIDTO;
import org.gycoding.books.application.dto.out.RatingODTO;
import org.gycoding.books.application.mapper.RatingServiceMapper;
import org.gycoding.books.domain.exceptions.BooksAPIError;
import org.gycoding.books.domain.model.RatingMO;
import org.gycoding.books.domain.repository.RatingRepository;
import org.gycoding.exceptions.model.APIException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository repository;
    private final RatingServiceMapper mapper;
    private final BookService bookService;

    @Override
    public RatingODTO getRating(String bookId, String userId) throws APIException {
        return repository.get(bookId, userId)
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
    public List<RatingODTO> listRatings(String userId) throws APIException {
        try {
            return repository.list(userId).stream()
                    .map(mapper::toODTO)
                    .toList();
        } catch (Exception e) {
            throw new APIException(
                    BooksAPIError.RESOURCE_NOT_FOUND.getCode(),
                    BooksAPIError.RESOURCE_NOT_FOUND.getMessage(),
                    BooksAPIError.RESOURCE_NOT_FOUND.getStatus()
            );
        }
    }

    @Override
    public RatingODTO saveRating(RatingIDTO rating) throws APIException {
        try {
            final var savedRating = mapper.toODTO(repository.save(mapper.toMO(rating)));

            bookService.refreshAverageRating(rating.bookId(), calculateAverageRating(rating), rating.userId());

            return savedRating;
        } catch (Exception e) {
            throw new APIException(
                    BooksAPIError.CONFLICT.getCode(),
                    BooksAPIError.CONFLICT.getMessage(),
                    BooksAPIError.CONFLICT.getStatus()
            );
        }
    }

    @Override
    public RatingODTO updateRating(RatingIDTO rating) throws APIException {
        try {
            final var updatedRating = mapper.toODTO(repository.update(mapper.toMO(rating)));

            bookService.refreshAverageRating(rating.bookId(), calculateAverageRating(rating), rating.userId());

            return updatedRating;
        } catch (Exception e) {
            throw new APIException(
                    BooksAPIError.CONFLICT.getCode(),
                    BooksAPIError.CONFLICT.getMessage(),
                    BooksAPIError.CONFLICT.getStatus()
            );
        }
    }

    private Number calculateAverageRating(RatingIDTO rating) {
        final var ratingList = new ArrayList<Double>(repository.list(rating.bookId())
                .stream()
                .map(ratingElement -> ratingElement.rating().doubleValue())
                .toList());

        ratingList.add(rating.rating().doubleValue());

        return ratingList.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }
}
