package com.digital.booking.adapter.input.controller.models.output;

import com.digital.booking.core.domain.User;
import com.digital.booking.core.domain.enums.Authority;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRestModel  implements Serializable {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Set<Authority> authorities;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public static UserRestModel fromDomain(User user){
        return UserRestModel.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .authorities(user.getAuthorities())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

}
