package com.swissquote.module.service;

import com.swissquote.module.dto.CashInOutRequest;
import com.swissquote.module.dto.TransferRequest;
import com.swissquote.module.entity.Transaction;

public interface TransactionService {
    Transaction cashIn(CashInOutRequest cashInOutRequest);

    Transaction cashOut(CashInOutRequest cashInOutRequest);

    Transaction transfer(TransferRequest transferRequest);
}
