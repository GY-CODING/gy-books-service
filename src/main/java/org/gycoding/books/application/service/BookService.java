package org.gycoding.books.application.service;
import org.gycoding.books.application.dto.in.BookIDTO;
import org.gycoding.books.application.dto.out.BookODTO;
import org.gycoding.exceptions.model.APIException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface BookService {
    BookODTO getBook(String id) throws APIException;
    BookODTO getBook(String id, UUID profileId) throws APIException;
    List<BookODTO> listBooks(UUID profileId, Pageable pageable) throws APIException;
    BookODTO updateBook(BookIDTO book) throws APIException;
}