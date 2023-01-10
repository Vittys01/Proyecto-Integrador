package com.digital.booking.core.exception;

public class EntityNotFoundException extends HttpErrorException{

    private static final int ENTITY_NOT_FOUND_EXCEPTION_CODE = 404;

    public EntityNotFoundException(ErrorCode errorCode) {
        super(ENTITY_NOT_FOUND_EXCEPTION_CODE, errorCode);
    }
}
