package com.digital.booking.core.usecases;

import com.digital.booking.core.domain.User;
import com.digital.booking.core.exception.EntityNotFoundException;
import com.digital.booking.core.exception.ErrorCode;
import com.digital.booking.core.port.input.UserAccountGettingQuery;
import com.digital.booking.core.port.output.SecurityJwtRepository;
import com.digital.booking.core.port.output.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAccountGettingUseCase implements UserAccountGettingQuery {


    private final UserRepository userJpaRestAdapter;

    private final SecurityJwtRepository jwtUtil;

    public UserAccountGettingUseCase(UserRepository userJpaRestAdapter, SecurityJwtRepository jwtUtil) {
        this.userJpaRestAdapter = userJpaRestAdapter;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public User execute(String token) {
        String userEmail = jwtUtil.getUserEmail(token);
        Optional<User> user = userJpaRestAdapter.getUserByEmail(userEmail);
        if(user.isEmpty()) throw new EntityNotFoundException(ErrorCode.USER_NOT_FOUND);
        return user.get();
    }
}
