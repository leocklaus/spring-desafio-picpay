package com.lck.picpaysimplificado.domain.repository;

import com.lck.picpaysimplificado.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
