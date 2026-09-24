package com.stantzy.mediary.exception;

import java.time.Instant;

public record ErrorDto(String message, Instant timestamp) {}
