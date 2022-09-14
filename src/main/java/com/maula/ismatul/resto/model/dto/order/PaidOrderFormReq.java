package com.maula.ismatul.resto.model.dto.order;

import com.maula.ismatul.resto.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaidOrderFormReq {
    private PaymentMethod paymentMethod;
}
