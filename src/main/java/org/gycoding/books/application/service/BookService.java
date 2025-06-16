package org.gycoding.books.application.service;
import org.gycoding.books.application.dto.in.RatingIDTO;
import org.gycoding.books.application.dto.out.BookODTO;
import org.gycoding.books.application.dto.out.RatingODTO;
import org.gycoding.books.domain.model.RatingMO;
import org.gycoding.exceptions.model.APIException;

import java.util.List;

public interface BookService {
    BookODTO getBook(String id) throws APIException;

    void refreshAverageRating(RatingMO rating) throws APIException;
}