package com.digital.booking.core.exception;

public class EntityConflictException extends HttpErrorException{

    private static final int ENTITY_CONFLICT_EXCEPTION_CODE = 409;

    public EntityConflictException( ErrorCode errorCode) {
        super(ENTITY_CONFLICT_EXCEPTION_CODE, errorCode);
    }
}
