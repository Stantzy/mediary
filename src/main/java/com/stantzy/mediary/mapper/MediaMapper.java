package com.stantzy.mediary.mapper;

import com.stantzy.mediary.domain.Media;
import com.stantzy.mediary.dto.request.MediaCreateRequest;
import com.stantzy.mediary.dto.response.MediaResponse;

public final class MediaMapper {
    private MediaMapper() {}

    public static Media toEntity(MediaCreateRequest request) {
        return Media.builder()
            .type(request.getType())
            .title(request.getTitle())
            .author(request.getAuthor())
            .description(request.getDescription())
            .build();
    }

    public static MediaResponse toResponse(Media media) {
        return MediaResponse.builder()
            .id(media.getId())
            .type(media.getType())
            .title(media.getTitle())
            .author(media.getAuthor())
            .description(media.getDescription())
            .createdAt(media.getCreatedAt())
            .build();
    }
}
