package com.stantzy.mediary.exception;

public class GenericEntityNotFoundException extends RuntimeException {
    private final String entityType;
    private final Long entityId;

    public GenericEntityNotFoundException(
        String entityType,
        Long entityId,
        String message
    ) {
        super(message);
        this.entityType = entityType;
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public Long getEntityId() {
        return entityId;
    }
}
