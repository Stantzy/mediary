package com.stantzy.mediary.mapper;

import com.stantzy.mediary.domain.Review;
import com.stantzy.mediary.dto.request.ReviewCreateRequest;
import com.stantzy.mediary.dto.response.ReviewResponse;

public final class ReviewMapper {
    private ReviewMapper() {}

    public static Review toEntity(ReviewCreateRequest request) {
        return Review.builder()
            .rating(request.getRating())
            .text(request.getText())
            .build();
    }

    public static ReviewResponse toResponse(Review review) {
        return ReviewResponse.builder()
            .id(review.getId())
            .mediaId(review.getMedia().getId()) // NPE
            .rating(review.getRating())
            .text(review.getText())
            .createdAt(review.getCreatedAt())
            .build();
    }
}
