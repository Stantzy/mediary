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
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getReviewById(
        @PathVariable(name = "id") Long id
    ) {
        ReviewResponse result = reviewService.getReviewById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> listReviews(
        @RequestParam(name = "mediaId", required = false) Long mediaId
    ) {
        List<ReviewResponse> result = (mediaId == null)
            ? reviewService.getAllReviews()
            : reviewService.getAllReviewsByMediaId(mediaId);

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ReviewResponse> createReview(
        @RequestBody ReviewCreateRequest request
    ) {
        ReviewResponse result = reviewService.createReview(request);
        URI location = URI.create("/api/reviews/" + result.getId());

        return ResponseEntity.created(location).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> updateReview(
        @PathVariable("id") Long id,
        @RequestBody ReviewUpdateRequest request
    ) {
        ReviewResponse result = reviewService.updateReview(id, request);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(
        @PathVariable(name = "id") Long id
    ) {
        reviewService.deleteReviewById(id);
        return ResponseEntity.noContent().build();
    }
}
