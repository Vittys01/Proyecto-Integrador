package com.digital.booking.adapter.input.controller;

import com.digital.booking.adapter.input.controller.models.input.ImageCreationRequestModel;

import com.digital.booking.adapter.input.controller.models.output.ImageRestModel;
import com.digital.booking.adapter.input.controller.models.output.CommonHttpRestModel;
import com.digital.booking.core.port.input.ImageCreationCommand;
import com.digital.booking.core.port.input.ImageDeletionCommand;
import com.digital.booking.core.port.input.ImageGettingQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageCreationCommand imageCreationUseCase;

    private final ImageGettingQuery imageGettingUseCase;

    private final ImageDeletionCommand imageDeletionUseCase;

    public ImageController(ImageCreationCommand imageCreationUseCase, ImageGettingQuery imageGettingUseCase,
            ImageDeletionCommand imageDeletionUseCase) {
        this.imageCreationUseCase = imageCreationUseCase;
        this.imageGettingUseCase = imageGettingUseCase;
        this.imageDeletionUseCase = imageDeletionUseCase;
    }

    @PostMapping
    ResponseEntity<CommonHttpRestModel<ImageRestModel>> save(@RequestBody ImageCreationRequestModel image) {
        log.info("Executing POST to /image/save {}", image);
        ImageCreationCommand.Data data = ImageCreationCommand.Data.builder()
                .url(image.getUrl())
                .build();
        ImageRestModel response = ImageRestModel.fromDomain(imageCreationUseCase.execute(data));
        log.info("Execution response {}", response);
        return ResponseEntity.ok(new CommonHttpRestModel<ImageRestModel>(true, response));
    }

    @GetMapping
    ResponseEntity<CommonHttpRestModel<Page<ImageRestModel>>> getPaginated(@RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size) {
        log.info("Executing GET to /image/get-paginated {}", Map.of("page", page, "size", size));
        ImageGettingQuery.Data data = ImageGettingQuery.Data.builder().page(page).size(size).build();
        Page<ImageRestModel> response = imageGettingUseCase.execute(data).map(ImageRestModel::fromDomain);
        log.info("Execution response {}", response.getContent());
        return ResponseEntity.ok(new CommonHttpRestModel<>(true, response));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<CommonHttpRestModel<String>> delete(@PathVariable(name = "id") Long id) {
        log.info("Executing delete to /image/delete/{id} {}", id);
        imageDeletionUseCase.execute(id);
        log.info("Execution response {}", id + " image deleted");
        return ResponseEntity.ok(new CommonHttpRestModel<>(true, null));
    }
}
