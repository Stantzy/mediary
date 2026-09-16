package com.stantzy.mediary.service;

import com.stantzy.mediary.domain.Media;
import com.stantzy.mediary.domain.Review;
import com.stantzy.mediary.dto.request.ReviewCreateRequest;
import com.stantzy.mediary.dto.request.ReviewUpdateRequest;
import com.stantzy.mediary.dto.response.ReviewResponse;
import com.stantzy.mediary.mapper.ReviewMapper;
import com.stantzy.mediary.repository.MediaRepository;
import com.stantzy.mediary.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MediaRepository mediaRepository;

    public ReviewResponse createReview(ReviewCreateRequest reviewCreateRequest) {
        Media media = mediaRepository.findById(reviewCreateRequest.getMediaId())
            .orElseThrow();

        Review reviewToCreate = ReviewMapper.toEntity(reviewCreateRequest);
        reviewToCreate.setMedia(media);

        Review savedReview = reviewRepository.save(reviewToCreate);

        return ReviewMapper.toResponse(savedReview);
    }

    public ReviewResponse getReviewById(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow();

        return ReviewMapper.toResponse(review);
    }

    public List<ReviewResponse> getAllReviews() {
        List<Review> reviewList = reviewRepository.findAll();
        List<ReviewResponse> reviewResponseList = new ArrayList<>();

        for(Review review : reviewList) {
            ReviewResponse reviewResponse = ReviewMapper.toResponse(review);
            reviewResponseList.add(reviewResponse);
        }

        return reviewResponseList;
    }

    public ReviewResponse updateReview(ReviewUpdateRequest reviewUpdateRequest) {
        Review reviewToUpdate = reviewRepository
            .findById(reviewUpdateRequest.getId())
            .orElseThrow();

        if(reviewUpdateRequest.getRating() != null)
            reviewToUpdate.setRating(reviewUpdateRequest.getRating());
        if(reviewUpdateRequest.getText() != null)
            reviewToUpdate.setText(reviewUpdateRequest.getText());

        Review updatedReview = reviewRepository.save(reviewToUpdate);

        return ReviewMapper.toResponse(updatedReview);
    }

    public void deleteReviewById(Long id) {
        reviewRepository.deleteById(id);
    }
}
