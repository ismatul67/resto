package com.maula.ismatul.resto.model.dto.food;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodFormCreateOrUpdateReq {
    private String name;
    private String description;
    private BigDecimal price;
}
