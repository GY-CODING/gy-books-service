package org.gycoding.books.application.service;
import org.gycoding.books.application.dto.in.RatingIDTO;
import org.gycoding.books.application.dto.out.RatingODTO;
import org.gycoding.exceptions.model.APIException;

import java.util.List;
import java.util.UUID;

public interface RatingService {
    RatingODTO getRating(String bookId, String userId) throws APIException;
    List<RatingODTO> listRatings(String userId) throws APIException;
    RatingODTO saveRating(RatingIDTO rating) throws APIException;
    RatingODTO updateRating(RatingIDTO rating) throws APIException;
}