package com.swissquote.module.service.impl;

import com.swissquote.module.dto.CashInOutRequest;
import com.swissquote.module.dto.TransferRequest;
import com.swissquote.module.entity.Transaction;
import com.swissquote.module.service.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Override
    public Transaction cashIn(CashInOutRequest cashInOutRequest) {
        return null;
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
