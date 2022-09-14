package com.maula.ismatul.resto.service;

import com.maula.ismatul.resto.model.entity.Transaction;
import com.maula.ismatul.resto.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction paid(Transaction transaction){
        return transactionRepository.save(transaction);
    }
}
