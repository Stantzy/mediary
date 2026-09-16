package com.stantzy.mediary.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "media")
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private MediaType type;

    @Column(unique = true)
    private String title;

    private String author;

    private String description;

    @OneToMany(mappedBy = "media")
    private List<Review> reviews;

    @Column(name = "created_at", nullable = false)
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
        private List<Review> reviews;
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

        public Builder reviews(List<Review> reviews) {
            this.reviews = reviews;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Media build() {
            validateFields();
            applyDefaults();

            Media media = new Media();

            media.setId(this.id);
            media.setTitle(this.title);
            media.setAuthor(this.author);
            media.setType(this.type);
            media.setDescription(this.description);
            media.setReviews(this.reviews);
            media.setCreatedAt(this.createdAt);

            return media;
        }

        private void validateFields() {
            if(this.id != null && this.id <= 0)
                throw new IllegalArgumentException("Id must be positive");

            if(this.title == null || title.isBlank())
                throw new IllegalArgumentException("Title can't be empty");
        }

        private void applyDefaults() {
            if(createdAt == null)
                createdAt = LocalDateTime.now();
        }
    }
}
