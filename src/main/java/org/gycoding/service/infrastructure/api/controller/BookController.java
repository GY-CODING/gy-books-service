package org.gycoding.service.infrastructure.api.controller;

import lombok.AllArgsConstructor;
import org.gycoding.service.application.service.BookService;
import org.gycoding.service.infrastructure.api.mapper.BookControllerMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService service;
    private final BookControllerMapper mapper;

    @GetMapping("")
    public ResponseEntity<?> queryBooks(
            @RequestParam String title,
            @RequestParam String author
    ){

        return ResponseEntity.ok(
                service.queryBooks(title, author).stream()
                        .map(mapper::toRSDTO)
                        .toList()
        );
    }


}