package com.digital.booking.core.port.output;

import com.digital.booking.core.domain.User;

public interface SecurityRepository {

    default String login(String email, String password, User user){throw new UnsupportedOperationException();};
}
