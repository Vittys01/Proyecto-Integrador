package com.digital.booking.adapter.input.controller;

import com.digital.booking.adapter.input.controller.models.input.CharacteristicPatchRequestModel;
import com.digital.booking.adapter.input.controller.models.input.RentalCreationRequestModel;
import com.digital.booking.adapter.input.controller.models.input.RentalPatchRequestModel;
import com.digital.booking.adapter.input.controller.models.output.CommonHttpRestModel;
import com.digital.booking.adapter.input.controller.models.output.RentalRestModel;
import com.digital.booking.core.domain.Image;
import com.digital.booking.core.port.input.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/rental")
public class RentalController {

    private final RentalCreationCommand rentalCreationUseCase;
    private final RentalGettingQuery rentalGettingUseCase;
    private final RentalPatchCommand rentalPatchUseCase;
    private final RentalDeletionCommand rentalDeletionUseCase;
    private final RentalSearchByIdCommand rentalSearchByIdUseCase;

    public RentalController(RentalCreationCommand rentalCreationUseCase, RentalGettingQuery rentalGettingUseCase, RentalPatchCommand rentalPatchUseCase, RentalDeletionCommand rentalDeletionUseCase, RentalSearchByIdCommand rentalSearchByIdUseCase) {
        this.rentalCreationUseCase = rentalCreationUseCase;
        this.rentalGettingUseCase = rentalGettingUseCase;
        this.rentalPatchUseCase = rentalPatchUseCase;
        this.rentalDeletionUseCase = rentalDeletionUseCase;
        this.rentalSearchByIdUseCase = rentalSearchByIdUseCase;
    }

    @GetMapping
    ResponseEntity<CommonHttpRestModel<Page<RentalRestModel>>> getPaginated(
            @RequestParam(name = "check_in", required = false) String checkIn,
            @RequestParam(name = "check_out", required = false) String checkOut,
            @RequestParam(name = "city", required = false) Long city,
            @RequestParam(name = "category", required = false) Long category,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "order", required = false, defaultValue = "ASC") String order,
            Principal auth) {
        log.info("Executing GET to /rental {}");

        RentalGettingQuery.Data.DataBuilder builder = RentalGettingQuery.Data.builder()
                .page(page)
                .size(size);

        if (checkIn != null && !checkIn.isBlank()) builder.checkIn(checkIn);
        if (checkOut != null && !checkIn.isBlank()) builder.checkOut(checkOut);
        if (city != null) builder.city(city);
        if (category != null) builder.category(category);
        if (order != null) builder.order(order);
        if (auth != null) builder.currentUser(auth.getName());

        Page<RentalRestModel> response = rentalGettingUseCase.execute(builder.build()).map(RentalRestModel::fromDomain);

        log.info("Execution response {}", response.getContent());
        return ResponseEntity.ok(new CommonHttpRestModel<>(true, response));
    }


    @GetMapping("/{id}")
    ResponseEntity<CommonHttpRestModel<RentalRestModel>> searchRentalById(@PathVariable(name = "id") Long id) {
        log.info("Executing GET to /rental/{id} {}", id);
        RentalRestModel response = RentalRestModel.fromDomain(rentalSearchByIdUseCase.execute(id).get());
        return ResponseEntity.ok(new CommonHttpRestModel<>(true, response));
    }

    @PostMapping
    ResponseEntity<CommonHttpRestModel<RentalRestModel>> save(@RequestBody RentalCreationRequestModel product) {
        log.info("Executing POST to /rental {}", product);
        RentalCreationCommand.Data data = RentalCreationCommand.Data.builder()
                .name(product.getName())
                .description(product.getDescription())
                .categoryId(product.getCategoryId())
                .cityId(product.getCityId())
                .distance(product.getDistance())
                .images(product.getImages().stream().map(new Function<String, Image>() {
                    @Override
                    public Image apply(String s) {
                        return Image.builder()
                                .url(s)
                                .build();
                    }
                }).collect(Collectors.toSet()))
                .characteristics(product.getCharacteristics().stream().map(CharacteristicPatchRequestModel::toDomain).collect(Collectors.toSet()))
                .build();
        RentalRestModel response = RentalRestModel.fromDomain(rentalCreationUseCase.execute(data));
        log.info("Execution response {}", response);
        return ResponseEntity.ok(new CommonHttpRestModel<RentalRestModel>(true, response));
    }

    @PatchMapping
    ResponseEntity<CommonHttpRestModel<RentalRestModel>> patch(@RequestBody RentalPatchRequestModel product) {
        log.info("Executing PATCH to /rental {}", product);
        RentalPatchCommand.Data data = RentalPatchCommand.Data.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .categoryId(product.getCategoryId())
                .cityId(product.getCityId())
                .distance(product.getDistance())
                .build();

        RentalRestModel response = RentalRestModel.fromDomain(rentalPatchUseCase.execute(data));
        log.info("Execution response {}", response);
        return ResponseEntity.ok(new CommonHttpRestModel<>(true, response));
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<CommonHttpRestModel<String>> delete(@PathVariable(name = "id") Long id) {
        log.info("Executing DELETE to /rental/{id} {}", id);
        rentalDeletionUseCase.execute(id);
        log.info("Execution response {}", id + " Product deleted");
        return ResponseEntity.ok(new CommonHttpRestModel<>(true, null));
    }
}
