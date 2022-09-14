package com.maula.ismatul.resto.repository;

import com.maula.ismatul.resto.model.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
    Page<Food> findAllByNameContainsAndIsDelete(String title, boolean isDelete, Pageable pageable);
}
