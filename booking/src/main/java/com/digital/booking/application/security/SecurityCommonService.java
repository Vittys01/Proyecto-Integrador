package com.digital.booking.application.security;

import com.digital.booking.core.domain.User;
import com.digital.booking.core.exception.EntityNotFoundException;
import com.digital.booking.core.exception.ErrorCode;
import com.digital.booking.core.port.output.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@Transactional
public class SecurityCommonService implements UserDetailsService {

    private final UserRepository userRepository;

    public SecurityCommonService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userFound = userRepository.getUserByEmail(username);
        System.out.println(username);
        if(userFound.isEmpty()) throw new EntityNotFoundException(ErrorCode.USER_EMAIL_NOT_FOUND);

        List<GrantedAuthority> authorities = userFound.get().getAuthorities()
                .stream().map(authority -> new SimpleGrantedAuthority(authority.name()))
                .peek(simpleGrantedAuthority -> log.info("Role {}", simpleGrantedAuthority))
                .collect(Collectors.toList());

        User user = userFound.get();
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                user.getStatus(), true, true, user.getStatus(), authorities);
    }
}
