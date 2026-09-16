package com.stantzy.mediary.dto.request;

import com.stantzy.mediary.domain.MediaType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MediaCreateRequest {
    private MediaType type;
    private String title;
    private String author;
    private String description;
}
