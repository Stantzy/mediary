package com.stantzy.mediary.controller;

import com.stantzy.mediary.dto.request.ReviewCreateRequest;
import com.stantzy.mediary.dto.request.ReviewUpdateRequest;
import com.stantzy.mediary.dto.response.ReviewResponse;
import com.stantzy.mediary.utils.ResponseWrapper;
import com.stantzy.mediary.service.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getReviewById(
        @PathVariable(name = "id") Long id
    ) {
//        try {
//            ReviewResponse result = reviewService.getReviewById(id);
//            return ResponseEntity.ok(result);
//        } catch(EntityNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        }
        return ResponseWrapper.handleOk(
            () -> reviewService.getReviewById(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> listReviews(
        @RequestParam(name = "mediaId", required = false) Long mediaId
    ) {
    List<ReviewResponse> reviews = (mediaId == null)
        ? reviewService.getAllReviews()
        : reviewService.getAllReviewsByMediaId(mediaId);

        return ResponseEntity.ok(reviews);
    }

    @PostMapping
    public ResponseEntity<ReviewResponse> createReview(
        @RequestBody ReviewCreateRequest request
    ) {
//        try {
//            ReviewResponse result = reviewService.createReview(request);
//            URI location = URI.create("/api/reviews/" + result.getId());
//
//            return ResponseEntity.created(location).body(result);
//        } catch(EntityNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        }

        return ResponseWrapper.handleCreated(
            () -> reviewService.createReview(request)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> updateReview(
        @PathVariable("id") Long id,
        @RequestBody ReviewUpdateRequest request
    ) {
        try {
            ReviewResponse updatedReview = reviewService.updateReview(id, request);
            return ResponseEntity.ok(updatedReview);
        } catch(EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(
        @PathVariable(name = "id") Long id
    ) {
        try {
            reviewService.deleteReviewById(id);
            return ResponseEntity.noContent().build();
        } catch(EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
