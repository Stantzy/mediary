package com.stantzy.mediary.service;

import com.stantzy.mediary.domain.Media;
import com.stantzy.mediary.dto.request.MediaCreateRequest;
import com.stantzy.mediary.dto.request.MediaUpdateRequest;
import com.stantzy.mediary.dto.response.MediaResponse;
import com.stantzy.mediary.exception.MediaNotFoundException;
import com.stantzy.mediary.mapper.MediaMapper;
import com.stantzy.mediary.repository.MediaRepository;
import com.stantzy.mediary.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaService {
    private final MediaRepository mediaRepository;
    private final ReviewRepository reviewRepository;

    public MediaResponse getMediaById(Long mediaId) {
        Media media = findByIdOrThrow(mediaId);
        BigDecimal averageRating =
            reviewRepository.findAverageRatingByMediaId(mediaId);
        Long reviewCount = reviewRepository.countByMediaId(mediaId);

        media.setAverageRating(
            averageRating == null ? BigDecimal.ZERO : averageRating
        );
        media.setReviewCount(reviewCount);

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
        Media mediaToCreate = MediaMapper.toEntity(request);
        Media createdMedia = mediaRepository.save(mediaToCreate);

        return MediaMapper.toResponse(createdMedia);
    }

    public void deleteMedia(Long mediaId) {
        Media mediaToDelete = findByIdOrThrow(mediaId);
        mediaRepository.delete(mediaToDelete);
    }

    public MediaResponse updateMedia(
        Long id,
        MediaUpdateRequest request
    ) {
        Media mediaToUpdate = findByIdOrThrow(id);

        mediaToUpdate.setType(request.getType());
        mediaToUpdate.setTitle(request.getTitle());
        mediaToUpdate.setAuthor(request.getAuthor());
        mediaToUpdate.setDescription(request.getDescription());

        Media updatedMedia = mediaRepository.save(mediaToUpdate);

        return MediaMapper.toResponse(updatedMedia);
    }

    private Media findByIdOrThrow(Long id) {
        return mediaRepository.findById(id)
            .orElseThrow(
                () -> new MediaNotFoundException(
                    "Not found Media by id=" + id
                )
            );
    }
}
