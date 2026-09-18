package com.stantzy.mediary.service;

import com.stantzy.mediary.domain.Media;
import com.stantzy.mediary.dto.request.MediaCreateRequest;
import com.stantzy.mediary.dto.request.MediaUpdateRequest;
import com.stantzy.mediary.dto.response.MediaResponse;
import com.stantzy.mediary.mapper.MediaMapper;
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

        return MediaMapper.toResponse(media);
    }

    public List<MediaResponse> listAllMedia() {
        List<Media> mediaList = mediaRepository.findAll();
        List<MediaResponse> mediaResponseList = new ArrayList<>();

        for(Media media : mediaList) {
            MediaResponse mediaResponse = MediaMapper.toResponse(media);
            mediaResponseList.add(mediaResponse);
        }

        return mediaResponseList;
    }

    public MediaResponse createMedia(MediaCreateRequest request) {
        Media mediaToCreate = initMediaFromCreateRequest(request);
        Media createdMedia = mediaRepository.save(mediaToCreate);

        return MediaMapper.toResponse(createdMedia);
    }

    private Media initMediaFromCreateRequest(MediaCreateRequest request) {
        return MediaMapper.toEntity(request);
    }

    public void deleteMedia(Long mediaId) {
        mediaRepository.deleteById(mediaId);
    }

    public MediaResponse updateMedia(
        Long id,
        MediaUpdateRequest request
    ) {
        Media mediaToUpdate = mediaRepository.findById(id)
            .orElseThrow();

        mediaToUpdate.setType(request.getType());
        mediaToUpdate.setTitle(request.getTitle());
        mediaToUpdate.setAuthor(request.getAuthor());
        mediaToUpdate.setDescription(request.getDescription());

        Media updatedMedia = mediaRepository.save(mediaToUpdate);

        return MediaMapper.toResponse(updatedMedia);
    }
}
