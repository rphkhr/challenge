package com.swissquote.module.repository;

import com.swissquote.module.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, String> {
}
