package org.gycoding.books.application.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gycoding.books.application.dto.out.BookODTO;
import org.gycoding.books.application.mapper.BookServiceMapper;
import org.gycoding.books.domain.repository.GoogleBooksFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final GoogleBooksFacade facade;
    private final BookServiceMapper mapper;

    @Override
    public List<BookODTO> queryBooks(String title, String author) {

        return facade.queryBooks(title, author)
                .stream()
                .map(mapper::toODTO)
                .toList();
    }
}
