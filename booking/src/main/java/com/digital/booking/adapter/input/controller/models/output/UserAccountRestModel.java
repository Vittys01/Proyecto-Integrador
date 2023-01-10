package com.digital.booking.adapter.input.controller.models.output;


import com.digital.booking.core.domain.User;
import com.digital.booking.core.domain.enums.Authority;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserAccountRestModel implements Serializable {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Set<String> authorities;


    public  static UserAccountRestModel fromDomain(User user){
        return UserAccountRestModel.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .authorities(user.getAuthorities().stream().map(Authority::getValue).collect(Collectors.toSet()))
                .build();

    }
}
