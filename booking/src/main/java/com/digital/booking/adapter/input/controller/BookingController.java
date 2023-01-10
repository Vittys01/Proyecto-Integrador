package com.digital.booking.adapter.input.controller;


import com.digital.booking.adapter.input.controller.models.input.BookingRequestModel;
import com.digital.booking.adapter.input.controller.models.output.BookingRestModel;
import com.digital.booking.adapter.input.controller.models.output.CommonHttpRestModel;
import com.digital.booking.adapter.input.controller.models.output.UserAccountRestModel;
import com.digital.booking.core.exception.ErrorCode;
import com.digital.booking.core.exception.IdentityException;
import com.digital.booking.core.port.input.BookingCreationCommand;
import com.digital.booking.core.port.input.BookingGettingQuery;
import com.digital.booking.core.port.input.UserAccountGettingQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingCreationCommand bookingCreationUseCase;

    private final BookingGettingQuery bookingGettingQueryUseCase;

    private final UserAccountGettingQuery userAccountGettingUseCase;
    public BookingController(BookingCreationCommand bookingCreationUseCase, BookingGettingQuery bookingGettingQueryUseCase, UserAccountGettingQuery userAccountGettingUseCase) {
        this.bookingCreationUseCase = bookingCreationUseCase;
        this.bookingGettingQueryUseCase = bookingGettingQueryUseCase;
        this.userAccountGettingUseCase = userAccountGettingUseCase;
    }


    @PostMapping
    ResponseEntity<CommonHttpRestModel<BookingRestModel>> save(@RequestBody BookingRequestModel booking){
        log.info("Executing POST to /booking {}", booking);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        BookingCreationCommand.Data data  = BookingCreationCommand.Data.builder()
                .checkinTime(booking.getCheckInTime())
                .checkIn(LocalDate.parse(booking.getCheckIn(),formatter))
                .checkOut(LocalDate.parse(booking.getCheckOut(),formatter))
                .userId(booking.getUserId())
                .rentalId(booking.getRentalId())
                .build();
        BookingRestModel response = BookingRestModel.fromDomain(bookingCreationUseCase.execute(data));
        log.info("Execution response {}", response);
        return ResponseEntity.ok(new CommonHttpRestModel<>(true, response));
    }

    @GetMapping("/user")
    ResponseEntity<CommonHttpRestModel<Page<BookingRestModel>>> getByUser(
            @RequestParam(name = "page", defaultValue = "0") Integer pageParam,
            @RequestParam(name = "size", defaultValue = "10") Integer sizeParam,
            @RequestHeader(name = "Authorization") String authorization){
        log.info("Executing GET to /booking/user");
        final String bearer = "Bearer";
        if(authorization  != null && !authorization.startsWith(bearer)) throw new IdentityException(ErrorCode.INVALID_TOKEN);
        final String token = authorization.substring(7);
        UserAccountRestModel user =  UserAccountRestModel.fromDomain(userAccountGettingUseCase.execute(token));
        Integer page = pageParam != null ? pageParam : 0;
        Integer size = sizeParam != null ? sizeParam : 10;
        Page<BookingRestModel> response = bookingGettingQueryUseCase.getBookingsByUserId(user.getId(), page, size).map(BookingRestModel::fromDomain);
        log.info("Execution response {}", response);
        return ResponseEntity.ok(new CommonHttpRestModel<>(true, response));
    }
    
}
