package com.digital.booking.core.exception;

public class IdentityException extends HttpErrorException{
    private static final int IDENTITY_EXCEPTION_CODE  = 401;
    public IdentityException( ErrorCode errorCode) {
        super(IDENTITY_EXCEPTION_CODE, errorCode);
    }
}
