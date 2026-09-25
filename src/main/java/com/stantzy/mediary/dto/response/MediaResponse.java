package com.stantzy.mediary.dto.response;

import com.stantzy.mediary.domain.MediaType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class MediaResponse {
    private Long id;
    private MediaType type;
    private String title;
    private String author;
    private String description;
    private BigDecimal averageRating;
    private Long reviewCount;
    private LocalDateTime createdAt;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private MediaType type;
        private String title;
        private String author;
        private String description;
        private BigDecimal averageRating;
        private Long reviewCount;
        private LocalDateTime createdAt;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder type(MediaType type) {
            this.type = type;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder averageRating(BigDecimal averageRating) {
            this.averageRating = averageRating;
            return this;
        }

        public Builder reviewCount(Long reviewCount) {
            this.reviewCount = reviewCount;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public MediaResponse build() {
            MediaResponse mediaResponse = new MediaResponse();

            mediaResponse.setId(id);
            mediaResponse.setTitle(title);
            mediaResponse.setAuthor(author);
            mediaResponse.setType(type);
            mediaResponse.setDescription(description);
            mediaResponse.setAverageRating(averageRating);
            mediaResponse.setReviewCount(reviewCount);
            mediaResponse.setCreatedAt(createdAt);

            return mediaResponse;
        }
    }
}
