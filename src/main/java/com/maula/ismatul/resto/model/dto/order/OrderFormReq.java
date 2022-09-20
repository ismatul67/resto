package com.maula.ismatul.resto.model.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderFormReq {
    private Long billId;
    private String name;
    private Long foodId;
    private Integer quantity;
}
