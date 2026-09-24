package com.stantzy.mediary.controller;

import com.stantzy.mediary.dto.request.MediaCreateRequest;
import com.stantzy.mediary.dto.request.MediaUpdateRequest;
import com.stantzy.mediary.dto.response.MediaResponse;
import com.stantzy.mediary.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/media")
public class MediaController {
    private final MediaService mediaService;

    @GetMapping("/{id}")
    public ResponseEntity<MediaResponse> getMediaById(
        @PathVariable(name = "id") Long mediaId
    ) {
        MediaResponse result = mediaService.getMediaById(mediaId);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<MediaResponse>> listAllMedia() {
        return ResponseEntity.ok(mediaService.listAllMedia());
    }

    @PostMapping
    public ResponseEntity<MediaResponse> createMedia(
        @RequestBody MediaCreateRequest request
    ) {
        MediaResponse result = mediaService.createMedia(request);
        URI location = URI.create("/api/media/" + result.getId());

        return ResponseEntity.created(location).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedia(
        @PathVariable(name = "id") Long mediaId
    ) {
        mediaService.deleteMedia(mediaId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MediaResponse> updateMedia(
        @PathVariable("id") Long id,
        @RequestBody MediaUpdateRequest request
    ) {
        MediaResponse result = mediaService.updateMedia(id, request);
        return ResponseEntity.ok(result);
    }
}
