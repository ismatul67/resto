package com.maula.ismatul.resto.model.dto.billdetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillDetailFormCreateOrUpdate {
    private Long billId;
    private Long foodId;
    private Integer quantity;
}
