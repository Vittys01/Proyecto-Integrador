package com.digital.booking.adapter.input.controller.models.output;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public  class CommonHttpRestModel<T>  implements Serializable {
    private Boolean success;
    private T body;
}
