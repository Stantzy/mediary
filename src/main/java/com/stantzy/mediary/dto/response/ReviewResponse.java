package com.stantzy.mediary.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewResponse {
    private Long id;
    private Long mediaId;
    private Integer rating;
    private String text;
    private LocalDateTime createdAt;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Long mediaId;
        private Integer rating;
        private String text;
        private LocalDateTime createdAt;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder mediaId(Long mediaId) {
            this.mediaId = mediaId;
            return this;
        }

        public Builder rating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public ReviewResponse build() {
            ReviewResponse reviewResponse = new ReviewResponse();

            reviewResponse.setId(id);
            reviewResponse.setMediaId(mediaId);
            reviewResponse.setRating(rating);
            reviewResponse.setText(text);
            reviewResponse.setCreatedAt(createdAt);

            return reviewResponse;
        }
    }
}
