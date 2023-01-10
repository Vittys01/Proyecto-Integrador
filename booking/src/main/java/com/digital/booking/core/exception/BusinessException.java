package com.digital.booking.core.exception;

public class BusinessException extends HttpErrorException{

    private static final Integer BAD_REQUEST_CODE = 400;

    public BusinessException(ErrorCode errorCode) {
        super(BAD_REQUEST_CODE, errorCode);
    }
}
