package com.digital.booking.adapter.input.controller;

import com.digital.booking.adapter.input.controller.models.input.CityCreationRequestModel;
import com.digital.booking.adapter.input.controller.models.input.CityPatchRequestModel;
import com.digital.booking.adapter.input.controller.models.output.CityRestModel;
import com.digital.booking.adapter.input.controller.models.output.CommonHttpRestModel;
import com.digital.booking.core.port.input.CityCreationCommand;
import com.digital.booking.core.port.input.CityDeletionCommand;
import com.digital.booking.core.port.input.CityGettingQuery;
import com.digital.booking.core.port.input.CityPatchCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/city")
public class CityController {

    private final CityCreationCommand cityCreationUseCase;

    private final CityGettingQuery cityGettingUseCase;

    private final CityPatchCommand cityPatchUseCase;

    private final CityDeletionCommand cityDeletionUseCase;

    public CityController(CityCreationCommand cityCreationUseCase, CityGettingQuery cityGettingUseCase, CityPatchCommand cityPatchUseCase, CityDeletionCommand cityDeletionUseCase) {
        this.cityCreationUseCase = cityCreationUseCase;
        this.cityGettingUseCase = cityGettingUseCase;
        this.cityPatchUseCase = cityPatchUseCase;
        this.cityDeletionUseCase = cityDeletionUseCase;
    }

    @PostMapping
    ResponseEntity<CommonHttpRestModel<CityRestModel>> save(@RequestBody CityCreationRequestModel city){
        log.info("Executing POST to /city {}", city);
        CityCreationCommand.Data data = CityCreationCommand.Data.builder()
                .name(city.getName())
                .provinceId(city.getProvinceId())
                .build();
        CityRestModel response = CityRestModel.fromDomain(cityCreationUseCase.execute(data));
        log.info("Execution response {}", response);
        return ResponseEntity.ok(new CommonHttpRestModel<CityRestModel>(true, response));
    }

    @GetMapping
    ResponseEntity<CommonHttpRestModel<Page<CityRestModel>>> getPaginated(@RequestParam(name = "page") Integer page,
                                                                          @RequestParam(name = "size") Integer size){
        log.info("Executing GET to /City/get-paginated {}", Map.of("page", page, "size", size ));
        CityGettingQuery.Data data = CityGettingQuery.Data.builder().page(page).size(size).build();
        Page<CityRestModel> response = cityGettingUseCase.execute(data).map(CityRestModel::fromDomain);
        log.info("Execution response {}", response.getContent());
        return ResponseEntity.ok(new CommonHttpRestModel<>(true, response));
    }

    @PatchMapping
    ResponseEntity<CommonHttpRestModel<CityRestModel>> patch(@RequestBody CityPatchRequestModel city){
        log.info("Executing GET to /city/patch {}", city);
        CityPatchCommand.Data data = CityPatchCommand.Data.builder()
                .id(city.getId())
                .name(city.getName())
                .provinceId(city.getProvinceId())
                .build();
        CityRestModel response = CityRestModel.fromDomain(cityPatchUseCase.execute(data));
        log.info("Execution response {}", response);
        return ResponseEntity.ok(new CommonHttpRestModel<>(true, response));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<CommonHttpRestModel<String>> delete(@PathVariable(name = "id") Long id){
        log.info("Executing delete to /City/delete/{id} {}", id);
        cityDeletionUseCase.execute(id);
        log.info("Execution response {}", id + " City deleted");
        return ResponseEntity.ok(new CommonHttpRestModel<>(true, null));
    }
}
