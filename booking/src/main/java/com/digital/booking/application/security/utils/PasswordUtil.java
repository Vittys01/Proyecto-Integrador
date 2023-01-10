package com.digital.booking.application.security.utils;

import com.digital.booking.core.port.output.SecurityPasswordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PasswordUtil implements SecurityPasswordRepository {

    private final BCryptPasswordEncoder passwordEncoder;

    public PasswordUtil(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
