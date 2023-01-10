package com.digital.booking.adapter.input.controller;

import com.digital.booking.adapter.input.controller.models.input.ProvinceCreationRequestModel;
import com.digital.booking.adapter.input.controller.models.input.ProvincePatchRequestModel;
import com.digital.booking.adapter.input.controller.models.output.ProvinceRestModel;
import com.digital.booking.adapter.input.controller.models.output.CommonHttpRestModel;
import com.digital.booking.core.port.input.ProvinceCreationCommand;
import com.digital.booking.core.port.input.ProvinceDeletionCommand;
import com.digital.booking.core.port.input.ProvinceGettingQuery;
import com.digital.booking.core.port.input.ProvincePatchCommand;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/province")
public class ProvinceController {

    private final ProvinceCreationCommand provinceCreationUseCase;
    private final ProvinceGettingQuery provinceGettingUseCase;
    private final ProvincePatchCommand provincePatchUseCase;
    private final ProvinceDeletionCommand provinceDeletionUseCase;

    public ProvinceController(ProvinceCreationCommand provinceCreationUseCase, ProvinceGettingQuery provinceGettingUseCase, ProvincePatchCommand provincePatchUseCase, ProvinceDeletionCommand provinceDeletionUseCase) {
        this.provinceCreationUseCase = provinceCreationUseCase;
        this.provinceGettingUseCase = provinceGettingUseCase;
        this.provincePatchUseCase = provincePatchUseCase;
        this.provinceDeletionUseCase = provinceDeletionUseCase;
    }

    @PostMapping
    ResponseEntity<CommonHttpRestModel<ProvinceRestModel>> save(@RequestBody ProvinceCreationRequestModel province){
        log.info("Executing POST to /province {}", province);
        ProvinceCreationCommand.Data data = ProvinceCreationCommand.Data.builder()
                .name(province.getName())
                .countryId(province.getCountryId())
                .build();
        ProvinceRestModel response = ProvinceRestModel.fromDomain(provinceCreationUseCase.execute(data));
        log.info("Execution response {}", response);
        return ResponseEntity.ok(new CommonHttpRestModel<ProvinceRestModel>(true, response));
    }

    @GetMapping
    ResponseEntity<CommonHttpRestModel<Page<ProvinceRestModel>>> getPaginated(@RequestParam(name = "page") Integer page,
                                                                          @RequestParam(name = "size") Integer size){
        log.info("Executing GET to /province {}", Map.of("page", page, "size", size ));
        ProvinceGettingQuery.Data data = ProvinceGettingQuery.Data.builder().page(page).size(size).build();
        Page<ProvinceRestModel> response = provinceGettingUseCase.execute(data).map(ProvinceRestModel::fromDomain);
        log.info("Execution response {}", response.getContent());
        return ResponseEntity.ok(new CommonHttpRestModel<>(true, response));
    }

    @PatchMapping
    ResponseEntity<CommonHttpRestModel<ProvinceRestModel>> patch(@RequestBody ProvincePatchRequestModel province){
        log.info("Executing GET to /Province/patch {}", province);
        ProvincePatchCommand.Data data = ProvincePatchCommand.Data.builder()
                .id(province.getId())
                .name(province.getName())
                .build();
        ProvinceRestModel response = ProvinceRestModel.fromDomain(provincePatchUseCase.execute(data));
        log.info("Execution response {}", response);
        return ResponseEntity.ok(new CommonHttpRestModel<>(true, response));
    }

   @DeleteMapping("/{id}")
    ResponseEntity<CommonHttpRestModel<String>> delete(@PathVariable(name = "id") Long id){
        log.info("Executing delete to /Province/delete/{id} {}", id);
       provinceDeletionUseCase.execute(id);
        log.info("Execution response {}", id + " Province deleted");
        return ResponseEntity.ok(new CommonHttpRestModel<>(true, null));
    }
}
