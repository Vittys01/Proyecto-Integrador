package com.digital.booking.core.exception;

public enum ErrorCode {

    CITY_NOT_FOUND(ErrorConstants.CITY_NOT_FOUND),
    CHARACTERISTIC_NOT_FOUND(ErrorConstants.CHARACTERISTIC_NOT_FOUND),
    MISSING_CHARACTERISTIC_ID(ErrorConstants.MISSING_CHARACTERISTIC_ID),
    MISSING_PROVINCE_ID(ErrorConstants.MISSING_PROVINCE_ID),
    INVALID_EMAIL_OR_PASSWORD(ErrorConstants.INVALID_EMAIL_OR_PASSWORD),
    MISSING_CATEGORY_ID(ErrorConstants.MISSING_CATEGORY_ID),
    CATEGORY_TO_UPDATE_NOT_FOUND(ErrorConstants.CATEGORY_TO_UPDATE_NOT_FOUND),
    USER_NOT_FOUND(ErrorConstants.USER_NOT_FOUND),
    USER_EMAIL_NOT_FOUND(ErrorConstants.USER_EMAIL_NOT_FOUND),
    USER_EMAIL_ALREADY_IN_USE(ErrorConstants.USER_EMAIL_ALREADY_IN_USE),
    INVALID_USER_AUTHORITY(ErrorConstants.INVALID_USER_AUTHORITY),
    INVALID_PROPERTY_TYPE(ErrorConstants.INVALID_PROPERTY_TYPE),

    BAD_ARRIVAL_DATES(ErrorConstants.BAD_ARRIVAL_DATES),
    INVALID_TOKEN(ErrorConstants.INVALID_TOKEN),
    RENTAL_NOT_FOUND(ErrorConstants.RENTAL_NOT_FOUND),

    RENTAL_RESERVED(ErrorConstants.RENTAL_RESERVED),

    INVALID_SELECTED_DATE(ErrorConstants.INVALID_SELECTED_DATE);

    ErrorCode(String value) {}

    public static class ErrorConstants{

        public static final String INVALID_SELECTED_DATE = "INVALID_SELECTED_DATE";
        private static final String BAD_ARRIVAL_DATES = "BAD_ARRIVAL_DATES";
        private static final String INVALID_TOKEN = "INVALID_TOKEN";

        private static final String RENTAL_NOT_FOUND = "RENTAL_NOT_FOUND";

        private static final String USER_NOT_FOUND = "USER_NOT_FOUND";

        private static final String RENTAL_RESERVED = "RENTAL_RESERVED";
        private static final String CITY_NOT_FOUND = "CITY_NOT_FOUND";
        private static final String CHARACTERISTIC_NOT_FOUND = "CHARACTERISTIC_NOT_FOUND";
        private static final String MISSING_CHARACTERISTIC_ID = "MISSING_CHARACTERISTIC_ID";
        private static final String MISSING_PROVINCE_ID = "MISSING_PROVINCE_ID";
        private static final String INVALID_EMAIL_OR_PASSWORD = "INVALID_EMAIL_OR_PASSWORD";
        private static final String USER_EMAIL_ALREADY_IN_USE = "USER_EMAIL_ALREADY_IN_USE";
        private static final String INVALID_USER_AUTHORITY ="INVALID_USER_AUTHORITY";
        private static final String USER_EMAIL_NOT_FOUND = "USER_EMAIL_NOT_FOUND";
        private static final String INVALID_PROPERTY_TYPE = "INVALID_PROPERTY_TYPE";
        private static final String MISSING_CATEGORY_ID = "MISSING_CATEGORY_ID";
        private static final String CATEGORY_TO_UPDATE_NOT_FOUND = "CATEGORY_TO_UPDATE_NOT_FOUND";
    }
}
