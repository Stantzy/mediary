package com.stantzy.mediary.controller;

import com.stantzy.mediary.dto.request.ReviewCreateRequest;
import com.stantzy.mediary.dto.request.ReviewUpdateRequest;
import com.stantzy.mediary.dto.response.ReviewResponse;
import com.stantzy.mediary.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReviewService reviewService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnReviewWhenExists() throws Exception {
        ReviewResponse response = buildReviewResponse(1L, 2L, 10);
        when(reviewService.getReviewById(1L)).thenReturn(response);

        mockMvc.perform(get("/api/reviews/{id}", 1L))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.mediaId").value(2))
            .andExpect(jsonPath("$.rating").value(10))
            .andExpect(jsonPath("$.text").value("Some text"))
            .andExpect(jsonPath("$.createdAt").value("2000-01-01T00:00:00"));

        verify(reviewService).getReviewById(1L);
    }

    @Test
    void shouldReturnListOfReviewsWhenExist() throws Exception {
        final int REVIEW_NUMBER = 10;
        List<ReviewResponse> reviews = new ArrayList<>();

        for(int i = 0; i < REVIEW_NUMBER; i++) {
            ReviewResponse review = buildReviewResponse(i, i, i);
            reviews.add(review);
        }

        when(reviewService.getAllReviews()).thenReturn(reviews);

        mockMvc.perform(get("/api/reviews"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.size()").value(REVIEW_NUMBER))
            .andExpect(jsonPath("$[0].id").value(0))
            .andExpect(jsonPath("$[" + (REVIEW_NUMBER - 1) + "].id")
                .value(REVIEW_NUMBER - 1))
            .andExpect(jsonPath("$[0].mediaId").value(0))
            .andExpect(jsonPath("$[0].rating").value(0))
            .andExpect(jsonPath("$[0].text").value("Some text"))
            .andExpect(jsonPath("$[0].createdAt").value("2000-01-01T00:00:00"));

        verify(reviewService).getAllReviews();
    }

    @Test
    void shouldCreateReview() throws Exception {
        ReviewCreateRequest request =
            new ReviewCreateRequest(1L, 1, "Some text");
        ReviewResponse response = buildReviewResponse(1L, 1L, 1);
        String expectedLocation = "/api/reviews/" + response.getId();
        when(reviewService.createReview(any(ReviewCreateRequest.class)))
            .thenReturn(response);

        mockMvc.perform(post("/api/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
            )
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", expectedLocation))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.mediaId").value(1))
            .andExpect(jsonPath("$.rating").value(1))
            .andExpect(jsonPath("$.text").value("Some text"));

        ArgumentCaptor<ReviewCreateRequest> captor =
            ArgumentCaptor.forClass(ReviewCreateRequest.class);
        verify(reviewService).createReview(captor.capture());

        ReviewCreateRequest capturedRequest = captor.getValue();
        assertThat(capturedRequest.getText()).isEqualTo(request.getText());
        assertThat(capturedRequest.getRating()).isEqualTo(request.getRating());
    }

    @Test
    void shouldUpdateReview() throws Exception {
        ReviewUpdateRequest request =
            new ReviewUpdateRequest(1L, 1, "Updated text");
        ReviewResponse response = buildReviewResponse(1, 1, 1);
        response.setText("Updated text");

        when(reviewService.updateReview(any(ReviewUpdateRequest.class)))
            .thenReturn(response);

        mockMvc.perform(put("/api/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
            )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.mediaId").value(1))
            .andExpect(jsonPath("$.rating").value(1))
            .andExpect(jsonPath("$.text").value("Updated text"));

        ArgumentCaptor<ReviewUpdateRequest> captor =
            ArgumentCaptor.forClass(ReviewUpdateRequest.class);
        verify(reviewService).updateReview(captor.capture());

        ReviewUpdateRequest capturedRequest = captor.getValue();
        assertThat(capturedRequest.getId()).isEqualTo(request.getId());
        assertThat(capturedRequest.getRating()).isEqualTo(request.getRating());
        assertThat(capturedRequest.getText()).isEqualTo(request.getText());
    }

    @Test
    void shouldDeleteReview() throws Exception {
        Long id = 1L;
        mockMvc.perform(delete("/api/reviews/{id}", id))
            .andExpect(status().isNoContent());

        verify(reviewService).deleteReviewById(id);
    }

    private ReviewResponse buildReviewResponse
    (long id, long mediaId, int rating) {
        ReviewResponse reviewResponse = new ReviewResponse();

        reviewResponse.setId(id);
        reviewResponse.setMediaId(mediaId);
        reviewResponse.setRating(rating);
        reviewResponse.setText("Some text");
        reviewResponse.setCreatedAt(LocalDateTime.of(2000, 1, 1, 0, 0));

        return reviewResponse;
    }
}
