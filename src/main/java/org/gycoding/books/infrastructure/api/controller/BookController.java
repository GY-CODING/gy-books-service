package org.gycoding.books.infrastructure.api.controller;

import lombok.AllArgsConstructor;
import org.gycoding.books.application.service.BookService;
import org.gycoding.books.infrastructure.api.mapper.BookControllerMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService service;
    private final BookControllerMapper mapper;

    @GetMapping("")
    public ResponseEntity<?> queryBooks(
            @RequestParam String q
    ) {
        return ResponseEntity.ok(
                service.queryBooks(q).stream()
                        .map(mapper::toRSDTO)
                        .toList()
        );
    }
}