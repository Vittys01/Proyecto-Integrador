package com.digital.booking.core.port.input;

import lombok.Builder;

import java.util.List;

public interface UserLoginCommand {

    String execute(Data data);

    @lombok.Data
    @Builder
    class Data {
        private String email;
        private String password;
    }

}
