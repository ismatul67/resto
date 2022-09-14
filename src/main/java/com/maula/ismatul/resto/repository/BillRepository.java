package com.maula.ismatul.resto.repository;

import com.maula.ismatul.resto.model.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
