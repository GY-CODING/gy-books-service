package org.gycoding.books.infrastructure.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.gycoding.books.application.service.RatingService;
import org.gycoding.books.infrastructure.api.dto.in.RatingRQDTO;
import org.gycoding.books.infrastructure.api.mapper.RatingControllerMapper;
import org.gycoding.exceptions.model.APIException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/books/ratings")
public class RatingController {
    private final RatingService service;
    private final RatingControllerMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> getRating(
            @PathVariable("id") String bookId,
            @RequestHeader("x-user-id") String userId
    ) throws APIException {
        return ResponseEntity.ok(mapper.toRSDTO(service.getRating(bookId, userId)));
    }

    @GetMapping("")
    public ResponseEntity<?> listRatings(
            @RequestHeader("x-user-id") String userId
    ) throws APIException {
        return ResponseEntity.ok(
                service.listRatings(userId).stream()
                        .map(mapper::toRSDTO)
                        .toList()
        );
    }

    @PostMapping("")
    public ResponseEntity<?> saveRating(
            @RequestHeader("x-user-id") String userId,
            @Valid @RequestBody RatingRQDTO rating
    ) throws APIException {
        return ResponseEntity.ok(mapper.toRSDTO(service.saveRating(mapper.toIDTO(rating, userId))));
    }

    @PatchMapping("")
    public ResponseEntity<?> updateRating(
            @RequestHeader("x-user-id") String userId,
            @Valid @RequestBody RatingRQDTO rating
    ) throws APIException {
        return ResponseEntity.ok(mapper.toRSDTO(service.updateRating(mapper.toIDTO(rating, userId))));
    }
}