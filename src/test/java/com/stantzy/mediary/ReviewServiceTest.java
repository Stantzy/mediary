package com.stantzy.mediary;

import com.stantzy.mediary.domain.Media;
import com.stantzy.mediary.domain.Review;
import com.stantzy.mediary.dto.response.ReviewResponse;
import com.stantzy.mediary.repository.ReviewRepository;
import com.stantzy.mediary.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {
    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    @Test
    void shouldReturnReviewById() {
        Media media = new Media();
        media.setId(1L);

        Review review = new Review();
        review.setId(1L);
        review.setMedia(media);
        review.setRating(1);
        review.setText("Review text");
        review.setCreatedAt(LocalDateTime.now());

        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));

        ReviewResponse result = reviewService.getReviewById(1L);

        assertThat(result.getText()).isEqualTo("Review text");
        assertThat(result.getMediaId()).isEqualTo(1L);
        verify(reviewRepository).findById(1L);
    }
}
