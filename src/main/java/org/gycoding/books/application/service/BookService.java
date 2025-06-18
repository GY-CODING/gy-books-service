package org.gycoding.books.application.service;
import org.gycoding.books.application.dto.in.BookIDTO;
import org.gycoding.books.application.dto.in.RatingIDTO;
import org.gycoding.books.application.dto.out.BookODTO;
import org.gycoding.books.application.dto.out.RatingODTO;
import org.gycoding.books.domain.model.BookStatus;
import org.gycoding.books.domain.model.RatingMO;
import org.gycoding.exceptions.model.APIException;

import java.util.List;

public interface BookService {
    BookODTO getBook(String id) throws APIException;
    BookODTO getBook(String id, String userId) throws APIException;
    BookODTO updateBook(BookIDTO book, String userId) throws APIException;
    void refreshAverageRating(String bookId, Number rating, String userId) throws APIException;
}