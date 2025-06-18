package org.gycoding.books.domain.repository;

import org.gycoding.books.domain.model.RatingMO;
import org.gycoding.exceptions.model.APIException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RatingRepository {
    Optional<RatingMO> get(String bookId, String userId);
    List<RatingMO> listByBookID(String bookId);
    List<RatingMO> list(String userId);
    RatingMO save(RatingMO rating) throws APIException;
    RatingMO update(RatingMO rating) throws APIException;
}
