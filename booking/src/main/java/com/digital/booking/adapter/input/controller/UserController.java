package com.digital.booking.adapter.input.controller;

import com.digital.booking.adapter.input.controller.models.input.UserLoginRequestModel;
import com.digital.booking.adapter.input.controller.models.output.UserAccountRestModel;
import com.digital.booking.adapter.input.controller.models.output.UserLoggedRestModel;
import com.digital.booking.adapter.input.controller.models.input.UserRegisterRequestModel;
import com.digital.booking.adapter.input.controller.models.output.CommonHttpRestModel;
import com.digital.booking.adapter.input.controller.models.output.UserRestModel;
import com.digital.booking.core.exception.ErrorCode;
import com.digital.booking.core.exception.IdentityException;
import com.digital.booking.core.port.input.UserAccountGettingQuery;
import com.digital.booking.core.port.input.UserLoginCommand;
import com.digital.booking.core.port.input.UserRegisterCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRegisterCommand userRegisterUseCase;

    private final UserLoginCommand userLoginUseCase;

    private final UserAccountGettingQuery userAccountGettingUseCase;

    public UserController(UserRegisterCommand userRegisterUseCase,
                          UserLoginCommand userLoginUseCase,
                          UserAccountGettingQuery userAccountGettingUseCase) {
        this.userRegisterUseCase = userRegisterUseCase;
        this.userLoginUseCase = userLoginUseCase;
        this.userAccountGettingUseCase = userAccountGettingUseCase;
    }

    @PostMapping("/register")
    ResponseEntity<CommonHttpRestModel<UserRestModel>> register(@RequestBody UserRegisterRequestModel user){
        log.info("Executing POST to /user/register {}", user);
        UserRegisterCommand.Data data = UserRegisterCommand.Data.builder()
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
        UserRestModel response = UserRestModel.fromDomain(userRegisterUseCase.execute(data));
        log.info("Execution response {}", response);
        return ResponseEntity.ok(new CommonHttpRestModel<>(true, response));
    }

    @PostMapping("/login")
    ResponseEntity<CommonHttpRestModel<UserLoggedRestModel>> login(@RequestBody UserLoginRequestModel login){
        log.info("Executing POST to  /user/login {}", login);
        UserLoginCommand.Data data = UserLoginCommand.Data
                .builder().email(login.getEmail()).password(login.getPassword()).build();

        String token = userLoginUseCase.execute(data);
        UserLoggedRestModel response = UserLoggedRestModel.builder().token(token).build();
        log.info("Execution response {}", response);
        return ResponseEntity.ok(new CommonHttpRestModel<>(true, response));
    }


    @GetMapping("/account")
    ResponseEntity<CommonHttpRestModel<UserAccountRestModel>> getUserAccount(@RequestHeader(name = "Authorization") String authorization){
        log.info("Executing GET to /user/account");
        final String bearer = "Bearer";
        if(authorization  != null && !authorization.startsWith(bearer)) throw new IdentityException(ErrorCode.INVALID_TOKEN);
        final String token = authorization.substring(7);
        UserAccountRestModel response =  UserAccountRestModel.fromDomain(userAccountGettingUseCase.execute(token));
        log.info("Execution response {}", response);
        return ResponseEntity.ok(new CommonHttpRestModel<>(true, response));
    }
}
