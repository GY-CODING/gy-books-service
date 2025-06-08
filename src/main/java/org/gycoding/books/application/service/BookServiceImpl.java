package org.gycoding.books.application.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gycoding.books.application.dto.out.BookODTO;
import org.gycoding.books.application.dto.out.RatingODTO;
import org.gycoding.books.application.mapper.BookServiceMapper;
import org.gycoding.books.domain.repository.OpenLibraryFacade;
import org.gycoding.books.domain.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final RatingRepository ratingRepository;
    private final OpenLibraryFacade facade;
    private final BookServiceMapper mapper;

    @Override
    public List<BookODTO> queryBooks(String query) {
        return facade.queryBooks(query)
                .stream()
                .map(mapper::toODTO)
                .toList();
    }

    @Override
    public List<RatingODTO> listRatings(String userId) {
        return null;
    }
}
