package com.digital.booking.adapter.input.controller;

import com.digital.booking.adapter.input.controller.models.input.CountryCreationRequestModel;
import com.digital.booking.adapter.input.controller.models.input.CountryPatchRequestModel;
import com.digital.booking.adapter.input.controller.models.output.CountryRestModel;
import com.digital.booking.adapter.input.controller.models.output.CommonHttpRestModel;
import com.digital.booking.core.port.input.CountryCreationCommand;
import com.digital.booking.core.port.input.CountryDeletionCommand;
import com.digital.booking.core.port.input.CountryGettingQuery;
import com.digital.booking.core.port.input.CountryPatchCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/country")
public class CountryController {

    private final CountryCreationCommand countryCreationUseCase;

    private final CountryGettingQuery countryGettingUseCase;

    private final CountryPatchCommand countryPatchUseCase;

    private final CountryDeletionCommand countryDeletionUseCase;

    public CountryController(CountryCreationCommand countryCreationUseCase, CountryGettingQuery countryGettingUseCase, CountryPatchCommand countryPatchUseCase, CountryDeletionCommand countryDeletionUseCase) {
        this.countryCreationUseCase = countryCreationUseCase;
        this.countryGettingUseCase = countryGettingUseCase;
        this.countryPatchUseCase = countryPatchUseCase;
        this.countryDeletionUseCase = countryDeletionUseCase;
    }

    @PostMapping
    ResponseEntity<CommonHttpRestModel<CountryRestModel>> save(@RequestBody  CountryCreationRequestModel country){
        log.info("Executing POST to /Country/save {}", country);
        CountryCreationCommand.Data data = CountryCreationCommand.Data.builder()
                .name(country.getName())
                .build();
        CountryRestModel response = CountryRestModel.fromDomain(countryCreationUseCase.execute(data));
        log.info("Execution response {}", response);
        return ResponseEntity.ok(new CommonHttpRestModel<CountryRestModel>(true, response));
    }

    @GetMapping
    ResponseEntity<CommonHttpRestModel<Page<CountryRestModel>>> getPaginated(@RequestParam(name = "page") Integer page,
                                                                             @RequestParam(name = "size") Integer size){
        log.info("Executing GET to /Country/get-paginated {}", Map.of("page", page, "size", size ));
        CountryGettingQuery.Data data = CountryGettingQuery.Data.builder().page(page).size(size).build();
        Page<CountryRestModel> response = countryGettingUseCase.execute(data).map(CountryRestModel::fromDomain);
        log.info("Execution response {}", response.getContent());
        return ResponseEntity.ok(new CommonHttpRestModel<>(true, response));
    }

    @PatchMapping
    ResponseEntity<CommonHttpRestModel<CountryRestModel>> patch(@RequestBody CountryPatchRequestModel country){
        log.info("Executing GET to /Country/patch {}", country);
        CountryPatchCommand.Data data = CountryPatchCommand.Data.builder()
                .id(country.getId())
                .name(country.getName())
                .build();
        CountryRestModel response = CountryRestModel.fromDomain(countryPatchUseCase.execute(data));
        log.info("Execution response {}", response);
        return ResponseEntity.ok(new CommonHttpRestModel<>(true, response));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<CommonHttpRestModel<String>> delete(@PathVariable(name = "id") Long id){
        log.info("Executing delete to /Country/delete/{id} {}", id);
        countryDeletionUseCase.execute(id);
        log.info("Execution response {}", id + " Country deleted");
        return ResponseEntity.ok(new CommonHttpRestModel<>(true, null));
    }
}
