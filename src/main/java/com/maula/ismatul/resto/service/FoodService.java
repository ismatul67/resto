package com.maula.ismatul.resto.service;

import com.maula.ismatul.resto.exception.NotFoundException;
import com.maula.ismatul.resto.exception.UnprocessableEntity;
import com.maula.ismatul.resto.model.dto.food.FoodFilterReq;
import com.maula.ismatul.resto.model.dto.food.FoodFormCreateOrUpdateReq;
import com.maula.ismatul.resto.model.entity.Food;
import com.maula.ismatul.resto.repository.FoodRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class FoodService {
    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Food create(FoodFormCreateOrUpdateReq form){
        Food food = Food.builder()
                .name(form.getName())
                .description(form.getDescription())
                .price(form.getPrice())
                .isDelete(false)
                .build();

        Food result = foodRepository.save(food);
        log.info("food {} with id {} has bean created", form.getName(), result.getId());

        return food;
    }

    public Food findById(Long id){
        return foodRepository.findById(id).orElseThrow(() -> new NotFoundException("Food not found"));
    }

    public Page<Food> findByName(FoodFilterReq form, Pageable pageable){
        return foodRepository.findAllByNameContainsAndIsDelete(form.getName(), false, pageable);
    }

    public Food update(Long id, FoodFormCreateOrUpdateReq form){
        Food food = findById(id);

        food.setName(form.getName());
        food.setDescription(form.getDescription());
        food.setPrice(form.getPrice());

        Food result = foodRepository.save(food);
        log.info("food {} with id {} has bean updated", form.getName(), result.getId());

        return food;
    }

    public void delete(Long id){
        Food food = findById(id);

        if (food.isDelete()){
            log.error("failed delete food with food id {}, because food was deleted ", id);
            throw new UnprocessableEntity("food was deleted");
        }

        food.setDelete(true);
        foodRepository.save(food);

        log.info("food {} with id {} has bean deleted", food.getName(), id);
    }

    public void restore(Long id){
        Food food = findById(id);

        if (!food.isDelete()){
            log.error("failed restore food with food id {}, because food was restored ", id);
            throw new UnprocessableEntity("food was restored");
        }

        food.setDelete(false);
        foodRepository.save(food);

        log.info("food {} with id {} has bean restored", food.getName(), id);
    }
}
