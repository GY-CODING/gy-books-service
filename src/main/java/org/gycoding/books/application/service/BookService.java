package org.gycoding.books.application.service;
import org.gycoding.books.application.dto.out.BookODTO;

import java.util.List;

public interface BookService {
    List<BookODTO> queryBooks(String title, String author);
}