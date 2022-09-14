package com.maula.ismatul.resto.service;

import com.maula.ismatul.resto.exception.NotFoundException;
import com.maula.ismatul.resto.model.entity.Bill;
import com.maula.ismatul.resto.repository.BillRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BillService {
    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Bill create(){
        return billRepository.save(new Bill());
    }

    public Bill findById(Long id){
        return billRepository.findById(id).orElseThrow(() -> new NotFoundException("Order Not Found"));
    }

    public Page<Bill> search(Pageable pageable){
        return billRepository.findAll(pageable);
    }
}
