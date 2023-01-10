package com.digital.booking.application.config;

import com.digital.booking.adapter.input.controller.models.output.CommonHttpRestModel;
import com.digital.booking.application.security.models.ErrorRestModel;
import com.digital.booking.core.exception.BusinessException;
import com.digital.booking.core.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandlerConfig {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<CommonHttpRestModel<ErrorRestModel>> handle(EntityNotFoundException ex){
        log.error("Error handled: ", ex);
        ErrorRestModel errorResponse = ErrorRestModel.builder().errorCode(ex.getErrorObject().getCode().name()).build();
        log.error("Error response: {}", errorResponse);
        return ResponseEntity.status(404).body(new CommonHttpRestModel<>(false, errorResponse));
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<CommonHttpRestModel<ErrorRestModel>> handle(BusinessException ex){
        log.error("Error handled: ", ex);
        ErrorRestModel errorResponse = ErrorRestModel.builder().errorCode(ex.getErrorObject().getCode().name()).build();
        log.error("Error response: {}", errorResponse);
        return ResponseEntity.status(400).body(new CommonHttpRestModel<>(false, errorResponse));
    }
}
