package com.stantzy.mediary.exception;

import java.time.Instant;

public record ErrorDto(Integer status, String message, Instant timestamp) {}
