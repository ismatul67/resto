package com.maula.ismatul.resto.controller;

import com.maula.ismatul.resto.model.dto.BaseFilter;
import com.maula.ismatul.resto.model.dto.BaseResponse;
import com.maula.ismatul.resto.model.dto.food.FoodFilterReq;
import com.maula.ismatul.resto.model.dto.food.FoodFormCreateOrUpdateReq;
import com.maula.ismatul.resto.model.entity.Food;
import com.maula.ismatul.resto.service.FoodService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/food")
public class FoodController extends BaseController{

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping()
    public ResponseEntity<BaseResponse<Object>> create(@RequestBody FoodFormCreateOrUpdateReq form) {
        return getResponseCreated(foodService.create(form), "Create Food Success");
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Object>> findById(@PathVariable Long id) {
        return getResponseOk(foodService.findById(id), "Food Found");
    }

    @PostMapping("/search")
    public ResponseEntity<BaseResponse<Page<?>>> search(BaseFilter baseFilter, @RequestBody FoodFilterReq filter){

        // TODO move value size to application.properties
        baseFilter.setSize(5);
        Page<Food> result = foodService.findByName(filter, getPageable(baseFilter));

        return getResponsePage(result, "List of food");
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Object>> update(@PathVariable Long id, @RequestBody FoodFormCreateOrUpdateReq form) {
        return getResponseOk(foodService.update(id, form), "Update Food Success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Object>> delete(@PathVariable Long id) {
        foodService.delete(id);
        return getResponseOk(null, "Delete Food Success");
    }

    @PutMapping("restore/{id}")
    public ResponseEntity<BaseResponse<Object>> restore(@PathVariable Long id) {
        foodService.restore(id);
        return getResponseOk(null, "Restore Food Success");
    }
}
