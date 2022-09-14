package com.maula.ismatul.resto.repository;

import com.maula.ismatul.resto.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
