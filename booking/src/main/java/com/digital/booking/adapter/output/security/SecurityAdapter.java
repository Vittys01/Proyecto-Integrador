package com.digital.booking.adapter.output.security;

import com.digital.booking.application.security.enums.EntityAuthorizationRole;
import com.digital.booking.application.security.models.UserSecurityDetails;
import com.digital.booking.core.domain.User;
import com.digital.booking.core.exception.BusinessException;
import com.digital.booking.core.exception.ErrorCode;
import com.digital.booking.core.port.output.SecurityJwtRepository;
import com.digital.booking.core.port.output.SecurityRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SecurityAdapter implements SecurityRepository {

    private final SecurityJwtRepository jwtUtil;

    private final AuthenticationManager authenticationManager;

    public SecurityAdapter(SecurityJwtRepository jwtUtil, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String login(String email, String password, User user) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
        }catch (BadCredentialsException e){
            throw  new BusinessException(ErrorCode.INVALID_EMAIL_OR_PASSWORD);
        }
        UserSecurityDetails securityDetails = UserSecurityDetails.builder()
                .userName(user.getEmail())
                .authorities(user.getAuthorities()
                        .stream()
                        .map(authority -> EntityAuthorizationRole.getFromValue(authority.getValue()))
                        .collect(Collectors.toSet()))
                .password(user.getPassword())
                .build();
        return jwtUtil.buildJwtToken(securityDetails);
    }
}
