package com.digital.booking.core.usecases;

import com.digital.booking.core.domain.User;
import com.digital.booking.core.exception.BusinessException;
import com.digital.booking.core.exception.ErrorCode;
import com.digital.booking.core.port.input.UserLoginCommand;
import com.digital.booking.core.port.output.SecurityRepository;
import com.digital.booking.core.port.output.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserLoginUseCase  implements UserLoginCommand {

    private final SecurityRepository securityRepository;

    private final UserRepository userRepository;

    public UserLoginUseCase(SecurityRepository securityRepository, UserRepository userRepository) {
        this.securityRepository = securityRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String execute(Data data) {
        Optional<User> userExistent = userRepository.getUserByEmail(data.getEmail());
        if(userExistent.isEmpty()) throw new BusinessException(ErrorCode.INVALID_EMAIL_OR_PASSWORD);
        return securityRepository.login(data.getEmail(), data.getPassword(), userExistent.get());
    }
}
