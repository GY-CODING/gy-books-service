package org.gycoding.books.infrastructure.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.gycoding.books.application.service.BookService;
import org.gycoding.books.application.service.RatingService;
import org.gycoding.books.infrastructure.api.dto.in.RatingRQDTO;
import org.gycoding.books.infrastructure.api.mapper.BookControllerMapper;
import org.gycoding.books.infrastructure.api.mapper.RatingControllerMapper;
import org.gycoding.exceptions.model.APIException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("")
public class BookController {
    private final BookService service;
    private final BookControllerMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> getBook(
            @PathVariable("id") String id
    ) throws APIException {
        return ResponseEntity.ok(mapper.toRSDTO(service.getBook(id)));
    }
}