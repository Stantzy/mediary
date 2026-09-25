package com.stantzy.mediary.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ReviewCreateRequest {
    @NotNull(message = "Media ID must not be null")
    private Long mediaId;

    @NotNull(message = "Rating must not be null")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 10, message = "Rating must be at most 10")
    private Integer rating;

    @NotBlank(message = "Text must not be blank")
    @Size(max = 5000, message = "Text must not exceed 5000 characters")
    private String text;
}
