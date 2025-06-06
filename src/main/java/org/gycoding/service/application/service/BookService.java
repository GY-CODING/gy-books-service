package org.gycoding.service.application.service;
import org.gycoding.service.application.dto.out.BookODTO;

import java.util.List;

public interface BookService {
    List<BookODTO> queryBooks(String title, String author);
}