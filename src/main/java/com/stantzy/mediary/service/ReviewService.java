package com.stantzy.mediary.service;

import com.stantzy.mediary.domain.Media;
import com.stantzy.mediary.domain.Review;
import com.stantzy.mediary.dto.request.ReviewCreateRequest;
import com.stantzy.mediary.dto.request.ReviewUpdateRequest;
import com.stantzy.mediary.dto.response.ReviewResponse;
import com.stantzy.mediary.exception.MediaNotFoundException;
import com.stantzy.mediary.exception.ReviewNotFoundException;
import com.stantzy.mediary.mapper.ReviewMapper;
import com.stantzy.mediary.repository.MediaRepository;
import com.stantzy.mediary.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private static final Logger logger =
        LoggerFactory.getLogger(ReviewService.class);

    private final ReviewRepository reviewRepository;
    private final MediaRepository mediaRepository;

    public ReviewResponse createReview(ReviewCreateRequest reviewCreateRequest) {
        Long mediaId = reviewCreateRequest.getMediaId();
        Media media = findMediaByIdOrThrow(mediaId);

        Review reviewToCreate = ReviewMapper.toEntity(reviewCreateRequest);
        reviewToCreate.setMedia(media);

        Review savedReview = reviewRepository.save(reviewToCreate);

        logger.info(
            "Review created: id={}, mediaId={}",
            savedReview.getId(),
            mediaId
        );

        return ReviewMapper.toResponse(savedReview);
    }

    public ReviewResponse getReviewById(Long id) {
        Review review = findReviewByIdOrThrow(id);
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

    public ReviewResponse updateReview(
        Long id,
        ReviewUpdateRequest reviewUpdateRequest
    ) {
        Review reviewToUpdate = findReviewByIdOrThrow(id);

        if(reviewUpdateRequest.getRating() != null)
            reviewToUpdate.setRating(reviewUpdateRequest.getRating());
        if(reviewUpdateRequest.getText() != null)
            reviewToUpdate.setText(reviewUpdateRequest.getText());

        Review updatedReview = reviewRepository.save(reviewToUpdate);

        logger.info("Review updated: id={}", updatedReview.getId());

        return ReviewMapper.toResponse(updatedReview);
    }

    public void deleteReviewById(Long id) {
        Review reviewToDelete = findReviewByIdOrThrow(id);
        Long mediaId = reviewToDelete.getMedia().getId();

        reviewRepository.delete(reviewToDelete);
        logger.info("Review deleted: id={}, mediaId={}", id, mediaId);
    }

    public List<ReviewResponse> getAllReviewsByMediaId(Long mediaId) {
        List<Review> reviews = reviewRepository.findAllByMediaId(mediaId);
        return reviews.stream()
            .map(ReviewMapper::toResponse)
            .toList();
    }

    private Media findMediaByIdOrThrow(Long id) {
        return mediaRepository.findById(id)
            .orElseThrow(
                () -> new MediaNotFoundException(
                    id,
                    "Not found Media by id=" + id
                )
            );
    }

    private Review findReviewByIdOrThrow(Long id) {
        return reviewRepository.findById(id)
            .orElseThrow(
                () -> new ReviewNotFoundException(
                    id,
                    "Not found Review by id=" + id
                )
            );
    }
}
