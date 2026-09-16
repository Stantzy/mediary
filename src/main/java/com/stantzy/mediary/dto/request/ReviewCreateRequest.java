package com.stantzy.mediary.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ReviewCreateRequest {
    private Long mediaId;
    private Integer rating;
    private String text;
}
