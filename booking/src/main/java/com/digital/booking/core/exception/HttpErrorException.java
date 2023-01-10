package com.digital.booking.core.exception;

import com.digital.booking.core.exception.model.ErrorObjectModel;
import lombok.Getter;

import java.util.Map;

@Getter
public class HttpErrorException extends RuntimeException{

    private final ErrorObjectModel errorObject;

    public HttpErrorException(int httpStatus, ErrorCode errorCode) {
        this.errorObject = ErrorObjectModel.builder()
                .httpStatus(httpStatus)
                .code(errorCode)
                .build();
    }

    public HttpErrorException(int httpStatus, ErrorCode errorCode, Map<String, String> fields) {
        this.errorObject = ErrorObjectModel.builder()
                .httpStatus(httpStatus)
                .code(errorCode)
                .fields(fields)
                .build();
    }

    public ErrorObjectModel getErrorObject(){return this.errorObject;}
}
