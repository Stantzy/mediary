package com.stantzy.mediary.exception;

public class ReviewNotFoundException extends GenericEntityNotFoundException {
    public ReviewNotFoundException(Long id, String message) {
        super("Review", id, message);
    }
}
