package org.gycoding.books.infrastructure.external.database.repository.impl;

import lombok.AllArgsConstructor;
import org.gycoding.books.domain.exceptions.BooksAPIError;
import org.gycoding.books.domain.model.RatingMO;
import org.gycoding.books.domain.repository.RatingRepository;
import org.gycoding.books.infrastructure.external.database.mapper.RatingRepositoryMapper;
import org.gycoding.books.infrastructure.external.database.repository.RatingMongoRepository;
import org.gycoding.exceptions.model.APIException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RatingRepositoryImpl implements RatingRepository {
    private final RatingMongoRepository repository;
    private final RatingRepositoryMapper mapper;

    @Override
    public Optional<RatingMO> get(String bookId, String userId) {
        return repository.findByBookIdAndUserId(bookId, userId)
                .map(mapper::toMO);
    }

    @Override
    public List<RatingMO> list(String userId) {
        return repository.findAllByUserId(userId).stream()
                .map(mapper::toMO)
                .toList();
    }

    @Override
    public RatingMO save(RatingMO rating) throws APIException {
        if(repository.findByBookIdAndUserId(rating.bookId(), rating.userId()).isPresent()) {
            throw new APIException(
                BooksAPIError.CONFLICT.getCode(),
                BooksAPIError.CONFLICT.getMessage(),
                BooksAPIError.CONFLICT.getStatus()
            );
        }

        return mapper.toMO(repository.save(mapper.toEntity(rating)));
    }

    @Override
    public RatingMO update(RatingMO rating) throws APIException {
        final var persistedRating = repository.findByBookIdAndUserId(rating.bookId(), rating.userId())
                .orElseThrow(() -> new APIException(
                        BooksAPIError.RESOURCE_NOT_FOUND.getCode(),
                        BooksAPIError.RESOURCE_NOT_FOUND.getMessage(),
                        BooksAPIError.RESOURCE_NOT_FOUND.getStatus()
                ));

        mapper.toUpdatedEntity(persistedRating, rating);

        return mapper.toMO(repository.save(persistedRating));
    }
}
