package com.digital.booking.adapter.input.controller.models.input;

import com.digital.booking.core.domain.Characteristic;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacteristicPatchRequestModel {

    private Long id;
    private String title;
    private String icon;

    public static Characteristic toDomain(CharacteristicPatchRequestModel characteristicPatchRequestModel){
        Characteristic characteristic = Characteristic.builder()
                .id(characteristicPatchRequestModel.getId())
                .title(characteristicPatchRequestModel.getTitle())
                .icon(characteristicPatchRequestModel.getIcon())
                .build();
        return characteristic;
    }
}
