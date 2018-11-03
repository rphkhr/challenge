package com.swissquote.module.service.impl;

import com.swissquote.module.dto.CashInOutRequest;
import com.swissquote.module.dto.TransferRequest;
import com.swissquote.module.entity.Account;
import com.swissquote.module.entity.Transaction;
import com.swissquote.module.repository.AccountRepository;
import com.swissquote.module.repository.TransactionRepository;
import com.swissquote.module.service.TransactionService;
import com.swissquote.module.utils.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public Transaction cashIn(CashInOutRequest cashInOutRequest) {
        Account account = accountRepository.findOne(cashInOutRequest.getAccountId());
        if (account == null) return null;

        Transaction transaction = new Transaction();
        transaction.setAmount(cashInOutRequest.getAmount());
        transaction.setCurrency(cashInOutRequest.getCurrency());
        transaction.setSourceAccount(account);
        transaction.setTransactionType(TransactionType.CASHIN.name());
        BigDecimal newAccountBalance = account.getBalance().add(cashInOutRequest.getAmount());

        accountRepository.setNewAccountBalance(account.getId(), newAccountBalance);

        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction cashOut(CashInOutRequest cashInOutRequest) {
        return null;
    }

    @Override
    public Transaction transfer(TransferRequest transferRequest) {
        return null;
    }
}
