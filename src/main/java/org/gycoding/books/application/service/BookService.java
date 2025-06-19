package org.gycoding.books.application.service;
import org.gycoding.books.application.dto.in.BookIDTO;
import org.gycoding.books.application.dto.out.BookODTO;
import org.gycoding.exceptions.model.APIException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    BookODTO getBook(String id) throws APIException;
    BookODTO getBook(String id, String userId) throws APIException;
    List<BookODTO> listBooks(String userId, Pageable pageable) throws APIException;
    BookODTO updateBook(BookIDTO book) throws APIException;
}