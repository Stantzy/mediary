package com.stantzy.mediary.exception;

public class MediaNotFoundException extends GenericEntityNotFoundException {
    public MediaNotFoundException(String message) {
        super(message);
    }
}
