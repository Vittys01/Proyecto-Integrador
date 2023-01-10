package com.digital.booking.core.port.output;

import com.digital.booking.core.domain.User;

import java.util.Optional;

public interface UserRepository extends CommonDaoRepository<User>{

    default Optional<User> getUserByEmail(String email){throw new UnsupportedOperationException();};
}
