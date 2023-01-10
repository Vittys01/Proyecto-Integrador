package com.digital.booking.core.domain;

import com.digital.booking.core.domain.enums.Authority;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Value
public class User {

    Long id;
    String name;
    String lastName;
    String email;
    String password;
    Boolean status;
    Set<Authority> authorities;
    City city;
    LocalDate createdAt;
    LocalDate updatedAt;
}
