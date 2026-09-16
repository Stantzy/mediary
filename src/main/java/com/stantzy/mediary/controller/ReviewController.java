package com.stantzy.mediary.controller;

import com.stantzy.mediary.dto.request.ReviewCreateRequest;
import com.stantzy.mediary.dto.request.ReviewUpdateRequest;
import com.stantzy.mediary.dto.response.ReviewResponse;
import com.stantzy.mediary.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/api/reviews/{id}")
    public ResponseEntity<ReviewResponse> getReviewById(
        @PathVariable(name = "id") Long id
    ) {
        ReviewResponse result = reviewService.getReviewById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/api/reviews")
    public ResponseEntity<List<ReviewResponse>> listReviews() {
        List<ReviewResponse> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/api/reviews")
    public ResponseEntity<ReviewResponse> ReviewCreateRequest(
        @RequestBody ReviewCreateRequest request
    ) {
        ReviewResponse result = reviewService.createReview(request);
        URI location = URI.create("/api/reviews/" + result.getId());

        return ResponseEntity.created(location).body(result);
    }

    @PutMapping("/api/reviews")
    public ResponseEntity<ReviewResponse> updateReview(
        @RequestBody ReviewUpdateRequest request
    ) {
        ReviewResponse updatedReview = reviewService.updateReview(request);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/api/reviews/{id}")
    public ResponseEntity<Void> deleteReview(
        @PathVariable(name = "id") Long id
    ) {
        reviewService.deleteReviewById(id);
        return ResponseEntity.noContent().build();
    }
}
