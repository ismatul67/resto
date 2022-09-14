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
    private Long orderId;
    private Long billId;
    private Long foodId;
    private Integer quantity;
}
