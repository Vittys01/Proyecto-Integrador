package com.digital.booking.core.port.output;

public interface SecurityPasswordRepository {

    default String encryptPassword(String password){throw new UnsupportedOperationException();}
}
