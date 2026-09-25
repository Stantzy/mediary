package com.stantzy.mediary.dto.request;

import com.stantzy.mediary.domain.MediaType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MediaUpdateRequest {
    @NotNull(message = "Media type must not be null")
    private MediaType type;

    @NotBlank(message = "Title must not be blank")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    @NotBlank(message = "Author must not be blank")
    @Size(max = 255)
    private String author;

    @Size(max = 2000, message = "Description must not exceed 2000 characters")
    private String description;
}
