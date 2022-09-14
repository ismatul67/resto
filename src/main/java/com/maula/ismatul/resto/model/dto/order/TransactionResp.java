package com.maula.ismatul.resto.model.dto.order;

import com.maula.ismatul.resto.enums.PaymentMethod;
import com.maula.ismatul.resto.model.entity.Bill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResp {
    private Long id;
    private PaymentMethod paymentMethod;
    private BigDecimal totalAmount;
    private Bill menu;
}
