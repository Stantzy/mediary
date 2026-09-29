package com.stantzy.mediary.exception;

public class MediaNotFoundException extends GenericEntityNotFoundException {
    public MediaNotFoundException(Long mediaId, String message) {
        super("Media", mediaId, message);
    }
}
