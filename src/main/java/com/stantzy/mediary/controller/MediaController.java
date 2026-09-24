package com.stantzy.mediary.controller;

import com.stantzy.mediary.dto.request.MediaCreateRequest;
import com.stantzy.mediary.dto.request.MediaUpdateRequest;
import com.stantzy.mediary.dto.response.MediaResponse;
import com.stantzy.mediary.utils.ResponseWrapper;
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
//        try {
//            return ResponseEntity.ok(mediaService.getMediaById(mediaId));
//        } catch(EntityNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        }
        return ResponseWrapper.handleOk(
            () -> mediaService.getMediaById(mediaId)
        );
    }

    @GetMapping
    public ResponseEntity<List<MediaResponse>> listAllMedia() {
        return ResponseEntity.ok(mediaService.listAllMedia());
    }

    @PostMapping
    public ResponseEntity<MediaResponse> createMedia(
        @RequestBody MediaCreateRequest request
    ) {
        MediaResponse response = mediaService.createMedia(request);
        URI location = URI.create("/api/media/" + response.getId());

        return ResponseEntity.created(location).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedia(
        @PathVariable(name = "id") Long mediaId
    ) {
//        try {
//            mediaService.deleteMedia(mediaId);
//            return ResponseEntity.noContent().build();
//        } catch(EntityNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        }
        return ResponseWrapper.handleNoContent(
            () -> mediaService.deleteMedia(mediaId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<MediaResponse> updateMedia(
        @PathVariable("id") Long id,
        @RequestBody MediaUpdateRequest request
    ) {
//        try {
//            return ResponseEntity.ok(mediaService.updateMedia(id, request));
//        } catch(EntityNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        }
        return ResponseWrapper.handleOk(
            () -> mediaService.updateMedia(id, request)
        );
    }
}
