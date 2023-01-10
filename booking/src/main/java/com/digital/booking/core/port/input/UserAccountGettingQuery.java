package com.digital.booking.core.port.input;

import com.digital.booking.core.domain.User;

public interface UserAccountGettingQuery {

    User execute(String token);
}
