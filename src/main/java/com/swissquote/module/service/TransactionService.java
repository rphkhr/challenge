package com.swissquote.module.service;

import com.swissquote.module.dto.CashInOutRequest;
import com.swissquote.module.dto.TransferRequest;
import com.swissquote.module.utils.SQResponse;

public interface TransactionService {
    SQResponse cashIn(CashInOutRequest cashInOutRequest);

    SQResponse cashOut(CashInOutRequest cashInOutRequest);

    SQResponse transfer(TransferRequest transferRequest);
}
