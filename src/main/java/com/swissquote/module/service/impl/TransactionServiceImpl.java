package com.swissquote.module.service.impl;

import com.swissquote.module.dto.CashInOutRequest;
import com.swissquote.module.dto.TransferRequest;
import com.swissquote.module.entity.Account;
import com.swissquote.module.entity.Transaction;
import com.swissquote.module.repository.AccountRepository;
import com.swissquote.module.repository.TransactionRepository;
import com.swissquote.module.service.TransactionService;
import com.swissquote.module.utils.SQResponse;
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
    public SQResponse cashIn(CashInOutRequest cashInOutRequest) {
        Account account = accountRepository.findOne(cashInOutRequest.getAccountId());
        if (account == null) return new SQResponse("Could not find account in DB");

        //Validate same currency
        if (!account.getCurrency().equalsIgnoreCase(cashInOutRequest.getCurrency())) {
            return new SQResponse("Currency conversion not available, try topping up in your account's currency");
        }


        //Create tx object
        Transaction transaction = new Transaction();
        transaction.setAmount(cashInOutRequest.getAmount());
        transaction.setCurrency(cashInOutRequest.getCurrency());
        transaction.setSourceAccount(account);
        transaction.setTransactionType(TransactionType.CASHIN.name());
        BigDecimal newAccountBalance = account.getBalance().add(cashInOutRequest.getAmount());

        accountRepository.setNewAccountBalance(account.getId(), newAccountBalance);

        return new SQResponse(transactionRepository.save(transaction));
    }

    @Override
    public SQResponse cashOut(CashInOutRequest cashInOutRequest) {
        Account account = accountRepository.findOne(cashInOutRequest.getAccountId());
        if (account == null) return null;

        if (account.getBalance().subtract(cashInOutRequest.getAmount()).compareTo(BigDecimal.ZERO) < 1) {
            return new SQResponse("Cannot withdraw more money than what you have in bank");
        }

        if (!account.getCurrency().equalsIgnoreCase(cashInOutRequest.getCurrency())) {
            return new SQResponse("Currency conversion not available, try withdrawing in your account's currency");
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(cashInOutRequest.getAmount());
        transaction.setCurrency(cashInOutRequest.getCurrency());
        transaction.setSourceAccount(account);
        transaction.setTransactionType(TransactionType.CASHOUT.name());
        BigDecimal newAccountBalance = account.getBalance().subtract(cashInOutRequest.getAmount());

        accountRepository.setNewAccountBalance(account.getId(), newAccountBalance);

        return new SQResponse(transactionRepository.save(transaction));
    }

    @Override
    public SQResponse transfer(TransferRequest request) {
        Account srcAccount = accountRepository.findOne(request.getSourceAccountId());
        if (srcAccount == null)
            return new SQResponse("Could not find source account in DB");


        Account dstAccount = accountRepository.findOne(request.getDestinationAccountId());
        if (dstAccount == null)
            return new SQResponse("Could not find target account in DB");


        if (srcAccount.getBalance().subtract(request.getAmount()).compareTo(BigDecimal.ZERO) < 1) {
            return new SQResponse("Cannot transfer more than what you have in DB");
        }

        //Validate currencies
        if (!srcAccount.getCurrency().equalsIgnoreCase(request.getCurrency())) {
            return new SQResponse("Currency conversion not available, try transferring from an account with the same currency as the request");
        }

        if (!dstAccount.getCurrency().equalsIgnoreCase(request.getCurrency())) {
            return new SQResponse("Currency conversion not available, try transferring from an account with the same currency as the request");
        }

        //Create tx object
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
        return new SQResponse(transactionRepository.save(transaction));
    }
}
