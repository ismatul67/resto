package com.maula.ismatul.resto.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maula.ismatul.resto.model.dto.BaseFilter;
import com.maula.ismatul.resto.model.dto.BaseResponse;
import com.maula.ismatul.resto.model.dto.order.OrderFormReq;
import com.maula.ismatul.resto.model.dto.order.PaidOrderFormReq;
import com.maula.ismatul.resto.model.entity.Bill;
import com.maula.ismatul.resto.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
public class OrderController extends BaseController{
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping()
    public ResponseEntity<BaseResponse<Object>> create(@RequestBody OrderFormReq form) {
        return getResponseCreated(orderService.add(form), "Add Food Success");
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Object>> findById(@PathVariable Long id) {
        return getResponseOk(orderService.findById(id), "Order Found");
    }

    @GetMapping()
    public ResponseEntity<BaseResponse<Page<?>>> search(BaseFilter baseFilter){
        Page<Bill> result = orderService.search(getPageable(baseFilter));
        return getResponsePage(result, "List of Order");
    }

    @PostMapping("/paid/{id}")
    public ResponseEntity<BaseResponse<Object>> create(@PathVariable Long id, @RequestBody PaidOrderFormReq form) throws JsonProcessingException {
        return getResponseOk(orderService.paid(id, form), "Paid Success");
    }
}
