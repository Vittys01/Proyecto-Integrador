package com.digital.booking.core.port.output;

import com.digital.booking.application.security.models.UserSecurityDetails;

public interface SecurityJwtRepository {

    default String  getUserEmail(String token){throw new UnsupportedOperationException();}

    default String buildJwtToken(UserSecurityDetails user){throw  new UnsupportedOperationException();}

    default Boolean isValidToken(String token, UserSecurityDetails securityDetails){throw  new UnsupportedOperationException();}

    default boolean isExpiredToken(String token){throw new UnsupportedOperationException();}

}
