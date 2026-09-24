package com.stantzy.mediary.exception;

public class ReviewNotFoundException extends GenericEntityNotFoundException {
    public ReviewNotFoundException(String message) {
        super(message);
    }
}
