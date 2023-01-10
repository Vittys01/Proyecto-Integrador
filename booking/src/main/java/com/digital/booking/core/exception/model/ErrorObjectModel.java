package com.digital.booking.core.exception.model;

import com.digital.booking.core.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Builder
@Value
public class ErrorObjectModel {

    @JsonIgnore
    int httpStatus;
    Map<String, String> fields;
    ErrorCode code;
}
