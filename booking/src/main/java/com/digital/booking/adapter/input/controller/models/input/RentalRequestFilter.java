package com.digital.booking.adapter.input.controller.models.input;

import lombok.*;

@Value
@Builder
public class RentalRequestFilter {
    private String checkIn;
    private String checkOut;
    private Long city;
    private Long category;
    private String order;
    private String currentUser;

    public Boolean isASC(){
        return  order.compareToIgnoreCase("ASC") == 0;
    }
}
