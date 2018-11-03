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
        Account account = accountRepository.findOne(cashInOutRequest.getAccountId());
        if (account == null) return null;

        if (account.getBalance().subtract(cashInOutRequest.getAmount()).compareTo(BigDecimal.ZERO) < 1) {
            return null;
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(cashInOutRequest.getAmount());
        transaction.setCurrency(cashInOutRequest.getCurrency());
        transaction.setSourceAccount(account);
        transaction.setTransactionType(TransactionType.CASHOUT.name());
        BigDecimal newAccountBalance = account.getBalance().subtract(cashInOutRequest.getAmount());

        accountRepository.setNewAccountBalance(account.getId(), newAccountBalance);

        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction transfer(TransferRequest request) {
        Account srcAccount = accountRepository.findOne(request.getSourceAccountId());
        if (srcAccount == null) return null;

        Account dstAccount = accountRepository.findOne(request.getDestinationAccountId());
        if (dstAccount == null) return null;

        if (srcAccount.getBalance().subtract(request.getAmount()).compareTo(BigDecimal.ZERO) < 1) {
            return null;
        }

        //Create transaction object
        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setCurrency(request.getCurrency());
        transaction.setSourceAccount(srcAccount);
        transaction.setTargetAccount(dstAccount);
        transaction.setTransactionType(TransactionType.TRANSFER.name());

        //Calculate new amounts & update
        BigDecimal newSourceAccountBalance = srcAccount.getBalance().subtract(request.getAmount());
        BigDecimal newDestinationAccountBalance = dstAccount.getBalance().add(request.getAmount());

        accountRepository.setNewAccountBalance(srcAccount.getId(), newSourceAccountBalance);

        accountRepository.setNewAccountBalance(dstAccount.getId(), newDestinationAccountBalance);

        // return saved transaction
        return transactionRepository.save(transaction);
    }
}
