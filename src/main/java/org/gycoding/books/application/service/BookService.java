package org.gycoding.books.application.service;
import org.gycoding.books.application.dto.out.BookODTO;
import org.gycoding.books.application.dto.out.RatingODTO;

import java.util.List;

public interface BookService {
    List<BookODTO> queryBooks(String query);
    List<RatingODTO> listRatings(String userId);
}