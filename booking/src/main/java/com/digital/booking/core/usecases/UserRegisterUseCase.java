package com.digital.booking.core.usecases;

import com.digital.booking.core.domain.User;
import com.digital.booking.core.domain.enums.Authority;
import com.digital.booking.core.exception.BusinessException;
import com.digital.booking.core.exception.EntityConflictException;
import com.digital.booking.core.exception.ErrorCode;
import com.digital.booking.core.port.input.UserRegisterCommand;
import com.digital.booking.core.port.output.SecurityPasswordRepository;
import com.digital.booking.core.port.output.UserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
public class UserRegisterUseCase implements UserRegisterCommand {

    private final SecurityPasswordRepository passwordUtil;

    private final Set<Authority> defaultUserAuthorities = Set.of(Authority.USER);

    private final UserRepository userRepository;

    public UserRegisterUseCase(SecurityPasswordRepository passwordUtil, UserRepository userRepository) {
        this.passwordUtil = passwordUtil;
        this.userRepository = userRepository;
    }

    @Override
    public User execute(Data data) {
        if(userRepository.getUserByEmail(data.getEmail()).isPresent()) throw new BusinessException(ErrorCode.USER_EMAIL_ALREADY_IN_USE);
        User user = User.builder()
                .name(data.getName())
                .lastName(data.getLastName())
                .email(data.getEmail())
                .password(passwordUtil.encryptPassword(data.getPassword()))
                .status(true)
                .authorities(defaultUserAuthorities)
                .createdAt(LocalDate.now())
                .updatedAt(null)
                .build();
        return userRepository.save(user);
    }
}
