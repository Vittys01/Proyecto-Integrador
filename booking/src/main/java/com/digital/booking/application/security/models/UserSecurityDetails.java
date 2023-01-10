package com.digital.booking.application.security.models;

import com.digital.booking.application.security.enums.EntityAuthorizationRole;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserSecurityDetails  {

    private Set<EntityAuthorizationRole> authorities;
    private String userName;
    private String password;

    public Boolean isAdmin(){
        return authorities.stream().filter(authority ->
                authority.name().equalsIgnoreCase(EntityAuthorizationRole.ADMIN.name()))
                .findFirst().isEmpty();
    }
}
