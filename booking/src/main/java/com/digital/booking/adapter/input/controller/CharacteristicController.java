package com.digital.booking.adapter.input.controller;

import com.digital.booking.adapter.input.controller.models.input.CharacteristicCreationRequestModel;
import com.digital.booking.adapter.input.controller.models.input.CharacteristicPatchRequestModel;
import com.digital.booking.adapter.input.controller.models.output.CharacteristicResponseModel;
import com.digital.booking.adapter.input.controller.models.output.CommonHttpRestModel;
import com.digital.booking.core.port.input.CharacteristicCreationCommand;
import com.digital.booking.core.port.input.CharacteristicDeletionCommand;
import com.digital.booking.core.port.input.CharacteristicGettingQuery;
import com.digital.booking.core.port.input.CharacteristicPatchCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/characteristic")
public class CharacteristicController {

    private final CharacteristicCreationCommand characteristicCreationCommand;
    private final CharacteristicGettingQuery characteristicGettingQuery;

    private final CharacteristicPatchCommand characteristicPatchCommand;

    private final CharacteristicDeletionCommand characteristicDeletionCommand;

    public CharacteristicController(CharacteristicCreationCommand characteristicCreationCommand, CharacteristicGettingQuery characteristicGettingQuery, CharacteristicPatchCommand characteristicPatchCommand, CharacteristicDeletionCommand characteristicDeletionCommand) {
        this.characteristicCreationCommand = characteristicCreationCommand;
        this.characteristicGettingQuery = characteristicGettingQuery;
        this.characteristicPatchCommand = characteristicPatchCommand;
        this.characteristicDeletionCommand = characteristicDeletionCommand;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CharacteristicCreationRequestModel characteristic) {
        log.info("Executing POST to /characteristic {}", characteristic);
        ResponseEntity response = new ResponseEntity<>(new CommonHttpRestModel<>(false, "Make sure all fields are completed"), HttpStatus.BAD_REQUEST);

        if (characteristic.getTitle() != null) {
            CharacteristicCreationCommand.Data data = CharacteristicCreationCommand.Data.builder()
                    .title(characteristic.getTitle())
                    .icon(characteristic.getIcon())
                    .build();

            CharacteristicResponseModel body = CharacteristicResponseModel.fromDomain(characteristicCreationCommand.execute(data));
            response = new ResponseEntity<>(new CommonHttpRestModel<>(true, body), HttpStatus.OK);
        }

        log.info("Execution response {}", response);
        return response;
    }

    @GetMapping
    ResponseEntity<CommonHttpRestModel<Page<CharacteristicResponseModel>>> getPaginated(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {

        log.info("Executing GET to /characteristic {}");

        CharacteristicGettingQuery.Data data = CharacteristicGettingQuery.Data.builder()
                .page(page)
                .size(size)
                .build();

        Page<CharacteristicResponseModel> response = characteristicGettingQuery.execute(data).map(CharacteristicResponseModel::fromDomain);

        log.info("Execution response {}");

        return ResponseEntity.ok(new CommonHttpRestModel<>(true, response));


    }

    @PatchMapping
    public ResponseEntity<?> patch(@RequestBody CharacteristicPatchRequestModel characteristic) {
        log.info("Executing PATCH to /characteristic {}", characteristic);
        ResponseEntity response = new ResponseEntity<>(new CommonHttpRestModel<>(false, "Make sure all fields are correct"), HttpStatus.BAD_REQUEST);

        if (characteristic.getId() != null) {
            CharacteristicPatchCommand.Data data = CharacteristicPatchCommand.Data.builder()
                    .id(characteristic.getId())
                    .title(characteristic.getTitle())
                    .icon(characteristic.getIcon())
                    .build();

            CharacteristicResponseModel body = CharacteristicResponseModel.fromDomain(characteristicPatchCommand.execute(data));
            response = new ResponseEntity<>(new CommonHttpRestModel<>(true, body), HttpStatus.OK);
        }

        log.info("Execution response {}", response);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        log.info("Executing DELETE /characteristic/{id} ", id);

        CharacteristicDeletionCommand.Data data = CharacteristicDeletionCommand.Data.builder()
                .id(id)
                .build();

        CharacteristicResponseModel body = CharacteristicResponseModel.fromDomain(characteristicDeletionCommand.execute(data));

        ResponseEntity response = new ResponseEntity<>(new CommonHttpRestModel<>(true, body), HttpStatus.OK);


        log.info("Execution response {}", response);

        return response;
    }

}
