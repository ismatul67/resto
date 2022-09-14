package com.maula.ismatul.resto.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maula.ismatul.resto.exception.UnprocessableEntity;
import com.maula.ismatul.resto.model.dto.billdetail.BillDetailFormCreateOrUpdate;
import com.maula.ismatul.resto.model.dto.order.OrderFormReq;
import com.maula.ismatul.resto.model.dto.order.PaidOrderFormReq;
import com.maula.ismatul.resto.model.dto.order.TransactionResp;
import com.maula.ismatul.resto.model.entity.Bill;
import com.maula.ismatul.resto.model.entity.BillDetail;
import com.maula.ismatul.resto.model.entity.Transaction;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Objects;

@Service
@Log4j2
public class OrderService {
    private final BillService billService;
    private final BillDetailService billDetailService;
    private final TransactionService transactionService;

    public OrderService(BillService billService, BillDetailService billDetailService, TransactionService transactionService) {
        this.billService = billService;
        this.billDetailService = billDetailService;
        this.transactionService = transactionService;
    }

    //TODO FIX RESPONSE
    @Transactional
    public Bill add(OrderFormReq form){
        Bill bill;
        if (Objects.nonNull(form.getBillId())){
            bill = billService.findById(form.getBillId());
        } else {
            bill = billService.create();
        }
        log.info(bill);
        BillDetailFormCreateOrUpdate createFormBillDetail = BillDetailFormCreateOrUpdate.builder()
                .billId(bill.getId())
                .foodId(form.getFoodId())
                .quantity(form.getQuantity())
                .build();
        billDetailService.create(createFormBillDetail);

        Bill byId = billService.findById(bill.getId());
        log.info(byId);
        return byId;
    }

    public Bill findById(Long id){
        return billService.findById(id);
    }

    public Page<Bill> search(Pageable pageable){
        return billService.search(pageable);
    }

    public TransactionResp paid(Long id, PaidOrderFormReq form) throws JsonProcessingException {
        Bill bill = billService.findById(id);

        if (null == bill.getOrderDetails()){
            throw new UnprocessableEntity("Cannot PAID this order because this order cannot food");
        }

        BigDecimal totalAmount  = BigDecimal.ZERO;
        for (BillDetail billDetail: bill.getOrderDetails()) {
            BigDecimal price = billDetail.getFood().getPrice().multiply(BigDecimal.valueOf(billDetail.getQuantity()));
            totalAmount = totalAmount.add(price);
        }

        String menu = "";

        ObjectMapper mapper = new ObjectMapper();
        try{
            menu = mapper.writeValueAsString(bill);
        } catch (JsonProcessingException e){
            log.error(e);
        }

        Transaction transaction = Transaction.builder()
                .paymentMethod(form.getPaymentMethod())
                .totalAmount(totalAmount)
                .menu(menu)
                .build();
        Transaction paid = transactionService.paid(transaction);

        Bill objectMenu = mapper.readValue(paid.getMenu(), Bill.class);

        return TransactionResp.builder()
                .id(paid.getId())
                .paymentMethod(paid.getPaymentMethod())
                .totalAmount(paid.getTotalAmount())
                .menu(objectMenu)
                .build();
    }
}
