package com.stantzy.mediary.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "media_id", referencedColumnName = "id", nullable = false)
    private Media media;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "text")
    private String text;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Media media;
        private Integer rating;
        private String text;
        private LocalDateTime createdAt;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder media(Media media) {
            this.media = media;
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

        public Review build() {
            validateFields();
            applyDefaults();

            Review review = new Review();

            review.setId(this.id);
            review.setMedia(this.media);
            review.setRating(this.rating);
            review.setText(this.text);
            review.setCreatedAt(this.createdAt);

            return review;
        }

        private void validateFields() {
            if(this.id != null && this.id <= 0)
                throw new IllegalArgumentException("Id must be positive");

            if(this.rating == null)
                throw new IllegalArgumentException("Rating is required");

            if(this.rating < 1 || this.rating > 10) {
                throw new IllegalArgumentException(
                    "Rating must be between 1 and 10"
                );
            }
        }

        private void applyDefaults() {
            if(createdAt == null)
                this.createdAt = LocalDateTime.now();
        }
    }
}
