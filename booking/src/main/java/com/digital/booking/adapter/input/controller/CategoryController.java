package com.digital.booking.adapter.input.controller;

import com.digital.booking.adapter.input.controller.models.input.CategoryCreationRequestModel;
import com.digital.booking.adapter.input.controller.models.input.CategoryPatchRequestModel;
import com.digital.booking.adapter.input.controller.models.output.CategoryRestModel;
import com.digital.booking.adapter.input.controller.models.output.CommonHttpRestModel;
import com.digital.booking.core.port.input.CategoryCreationCommand;
import com.digital.booking.core.port.input.CategoryDeletionCommand;
import com.digital.booking.core.port.input.CategoryGettingQuery;
import com.digital.booking.core.port.input.CategoryPatchCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryCreationCommand categoryCreationUseCase;

    private final CategoryGettingQuery categoryGettingUseCase;

    private final CategoryPatchCommand categoryPatchUseCase;

    private final CategoryDeletionCommand categoryDeletionUseCase;

    public CategoryController(CategoryCreationCommand categoryCreationUseCase, CategoryGettingQuery categoryGettingUseCase, CategoryPatchCommand categoryPatchUseCase, CategoryDeletionCommand categoryDeletionUseCase) {
        this.categoryCreationUseCase = categoryCreationUseCase;
        this.categoryGettingUseCase = categoryGettingUseCase;
        this.categoryPatchUseCase = categoryPatchUseCase;
        this.categoryDeletionUseCase = categoryDeletionUseCase;
    }

    @PostMapping
    ResponseEntity<CommonHttpRestModel<CategoryRestModel>> save(@RequestBody  CategoryCreationRequestModel category){
        log.info("Executing POST to /category {}", category);
        CategoryCreationCommand.Data data = CategoryCreationCommand.Data.builder()
                .name(category.getName())
                .description(category.getDescription())
                .image(category.getImage())
                .build();
        CategoryRestModel response = CategoryRestModel.fromDomain(categoryCreationUseCase.execute(data));
        log.info("Execution response {}", response);
        return ResponseEntity.ok(new CommonHttpRestModel<CategoryRestModel>(true, response));
    }

    @GetMapping
    ResponseEntity<CommonHttpRestModel<Page<CategoryRestModel>>> getPaginated(@RequestParam(name = "page") Integer page,
                                                         @RequestParam(name = "size") Integer size){
        log.info("Executing GET to /category {}", Map.of("page", page, "size", size ));
        CategoryGettingQuery.Data data = CategoryGettingQuery.Data.builder().page(page).size(size).build();
        Page<CategoryRestModel> response = categoryGettingUseCase.execute(data).map(CategoryRestModel::fromDomain);
        log.info("Execution response {}", response.getContent());
        return ResponseEntity.ok(new CommonHttpRestModel<>(true, response));
    }

    @PatchMapping
    ResponseEntity<CommonHttpRestModel<CategoryRestModel>> patch(@RequestBody CategoryPatchRequestModel category){
        log.info("Executing GET to /category {}", category);
        CategoryPatchCommand.Data data = CategoryPatchCommand.Data.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .image(category.getImage())
                .status(category.getStatus())
                .build();
        CategoryRestModel response = CategoryRestModel.fromDomain(categoryPatchUseCase.execute(data));
        log.info("Execution response {}", response);
        return ResponseEntity.ok(new CommonHttpRestModel<>(true, response));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<CommonHttpRestModel<String>> delete(@PathVariable(name = "id") Long id){
        log.info("Executing delete to /category/{id} {}", id);
        categoryDeletionUseCase.execute(id);
        log.info("Execution response {}", id + " category deleted");
        return ResponseEntity.ok(new CommonHttpRestModel<>(true, null));
    }
}
