package com.stantzy.mediary.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ReviewUpdateRequest {
    private Long id;
    private Integer rating;
    private String text;
}
