package com.digital.booking.adapter.input.controller.models.output;

import com.digital.booking.core.domain.Characteristic;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacteristicResponseModel implements Serializable {
    private Long id;
    private String title;
    private String icon;

    public static CharacteristicResponseModel fromDomain(Characteristic characteristic) {
        return CharacteristicResponseModel.builder()
                .id(characteristic.getId())
                .title(characteristic.getTitle())
                .icon(characteristic.getIcon())
                .build();
    }
}
