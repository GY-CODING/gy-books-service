package org.gycoding.books.infrastructure.api.controller;

import lombok.AllArgsConstructor;
import org.gycoding.books.application.service.BookService;
import org.gycoding.books.domain.repository.GYAccountsFacade;
import org.gycoding.books.infrastructure.api.dto.in.BookRQDTO;
import org.gycoding.books.infrastructure.api.mapper.BookControllerMapper;
import org.gycoding.exceptions.model.APIException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("")
public class BookController {
    private final BookService service;
    private final BookControllerMapper mapper;
    private final GYAccountsFacade accountsFacade;

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
        return ResponseEntity.ok(mapper.toRSDTO(service.getBook(id, accountsFacade.getProfileId(userId))));
    }

    @GetMapping("/{profileId}/list")
    public ResponseEntity<?> listBooks(
            @PathVariable("profileId") String profileId,
            Pageable pageable
            ) throws APIException {
        return ResponseEntity.ok(service.listBooks(UUID.fromString(profileId), pageable).stream()
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
        return ResponseEntity.ok(mapper.toRSDTO(service.updateBook(mapper.toIDTO(book, id, accountsFacade.getProfileId(userId)))));
    }
}