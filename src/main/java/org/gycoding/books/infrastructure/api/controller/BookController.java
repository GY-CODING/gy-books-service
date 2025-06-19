package org.gycoding.books.infrastructure.api.controller;

import lombok.AllArgsConstructor;
import org.gycoding.books.application.service.BookService;
import org.gycoding.books.infrastructure.api.dto.in.BookRQDTO;
import org.gycoding.books.infrastructure.api.mapper.BookControllerMapper;
import org.gycoding.exceptions.model.APIException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("")
public class BookController {
    private final BookService service;
    private final BookControllerMapper mapper;

    @GetMapping("/{id}/public")
    public ResponseEntity<?> getBookPublic(
            @PathVariable("id") String id
    ) throws APIException {
        return ResponseEntity.ok(mapper.toRSDTO(service.getBook(id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBook(
            @RequestHeader("x-user-id") String userId,
            @PathVariable("id") String id
    ) throws APIException {
        return ResponseEntity.ok(mapper.toRSDTO(service.getBook(id, userId)));
    }

    @GetMapping("")
    public ResponseEntity<?> listBooks(
            @RequestHeader("x-user-id") String userId
    ) throws APIException {
        return ResponseEntity.ok(service.listBooks(userId).stream()
                .map(mapper::toRSDTO)
                .toList()
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBook(
            @RequestHeader("x-user-id") String userId,
            @PathVariable("id") String id,
            @RequestBody BookRQDTO book
    ) throws APIException {
        return ResponseEntity.ok(mapper.toRSDTO(service.updateBook(mapper.toIDTO(book, id, userId))));
    }
}