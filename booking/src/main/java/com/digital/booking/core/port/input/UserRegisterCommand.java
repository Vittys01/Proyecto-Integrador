package com.digital.booking.core.port.input;

import com.digital.booking.core.domain.User;
import lombok.Builder;

public interface UserRegisterCommand {

    User execute(Data data);

    @lombok.Data
    @Builder
    class Data{
        private String email;
        private String name;
        private String lastName;
        private String password;
    }
}
