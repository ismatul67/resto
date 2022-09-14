package com.maula.ismatul.resto.service;

import com.maula.ismatul.resto.exception.NotFoundException;
import com.maula.ismatul.resto.model.dto.billdetail.BillDetailFormCreateOrUpdate;
import com.maula.ismatul.resto.model.entity.Bill;
import com.maula.ismatul.resto.model.entity.BillDetail;
import com.maula.ismatul.resto.model.entity.Food;
import com.maula.ismatul.resto.repository.BillDetailRepository;
import org.springframework.stereotype.Service;

@Service
public class BillDetailService {
    private final BillDetailRepository billdetailRepository;
    private final BillService billService;
    private final FoodService foodService;

    public BillDetailService(BillDetailRepository billdetailRepository, BillService billService, FoodService foodService) {
        this.billdetailRepository = billdetailRepository;
        this.billService = billService;
        this.foodService = foodService;
    }

    public BillDetail create(BillDetailFormCreateOrUpdate form){
        Bill bill = billService.findById(form.getBillId());
        Food food = foodService.findById(form.getFoodId());

        BillDetail billDetail = BillDetail.builder()
                .bill(bill)
                .food(food)
                .quantity(form.getQuantity())
                .build();
        return billdetailRepository.save(billDetail);
    }

    public BillDetail findById(Long id){
        return billdetailRepository.findById(id).orElseThrow(() -> new NotFoundException("Order Not Found"));
    }

    public BillDetail update(Long billId, BillDetailFormCreateOrUpdate form){
        BillDetail billDetail = findById(billId);
        Bill bill = billService.findById(form.getBillId());
        Food food = foodService.findById(form.getFoodId());

        billDetail.setBill(bill);
        billDetail.setFood(food);
        billDetail.setQuantity(form.getQuantity());

        return billdetailRepository.save(billDetail);
    }
}
