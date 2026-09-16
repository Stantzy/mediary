package com.stantzy.mediary.service;

import com.stantzy.mediary.domain.Media;
import com.stantzy.mediary.dto.request.MediaCreateRequest;
import com.stantzy.mediary.dto.request.MediaUpdateRequest;
import com.stantzy.mediary.dto.response.MediaResponse;
import com.stantzy.mediary.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaService {
    private final MediaRepository mediaRepository;

    public MediaResponse getMediaById(Long mediaId) {
        Media media = mediaRepository.findById(mediaId)
            .orElseThrow();

        return MediaResponse.builder()
            .id(media.getId())
            .title(media.getTitle())
            .author(media.getAuthor())
            .description(media.getDescription())
            .type(media.getType())
            .createdAt(media.getCreatedAt())
            .build();
    }

    public List<MediaResponse> listAllMedia() {
        List<Media> mediaList = mediaRepository.findAll();
        List<MediaResponse> mediaResponseList = new ArrayList<>();

        for(Media media : mediaList) {
            MediaResponse mediaResponse = MediaResponse.builder()
                .id(media.getId())
                .title(media.getTitle())
                .author(media.getAuthor())
                .description(media.getDescription())
                .type(media.getType())
                .createdAt(media.getCreatedAt())
                .build();

            mediaResponseList.add(mediaResponse);
        }

        return mediaResponseList;
    }

    public MediaResponse createMedia(MediaCreateRequest request) {
        Media mediaToCreate = initMediaFromCreateRequest(request);
        Media createdMedia = mediaRepository.save(mediaToCreate);

        return MediaResponse.builder()
            .id(createdMedia.getId())
            .title(createdMedia.getTitle())
            .author(createdMedia.getAuthor())
            .description(createdMedia.getDescription())
            .type(createdMedia.getType())
            .createdAt(createdMedia.getCreatedAt())
            .build();
    }

    private Media initMediaFromCreateRequest(MediaCreateRequest request) {
        return Media.builder()
            .title(request.getTitle())
            .author(request.getAuthor())
            .description(request.getDescription())
            .type(request.getType())
            .build();
    }

    public void deleteMedia(Long mediaId) {
        mediaRepository.deleteById(mediaId);
    }

    public MediaResponse updateMedia(MediaUpdateRequest request) {
        Media mediaToUpdate = mediaRepository.findById(request.getId())
            .orElseThrow();

        mediaToUpdate.setType(request.getType());
        mediaToUpdate.setTitle(request.getTitle());
        mediaToUpdate.setAuthor(request.getAuthor());
        mediaToUpdate.setDescription(request.getDescription());

        Media updatedMedia = mediaRepository.save(mediaToUpdate);

        return MediaResponse.builder()
            .id(updatedMedia.getId())
            .title(updatedMedia.getTitle())
            .author(updatedMedia.getAuthor())
            .description(updatedMedia.getDescription())
            .type(updatedMedia.getType())
            .createdAt(updatedMedia.getCreatedAt())
            .build();
    }
}
