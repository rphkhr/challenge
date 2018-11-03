package com.swissquote.module.controller;

import com.swissquote.module.dto.CashInOutRequest;
import com.swissquote.module.dto.TransferRequest;
import com.swissquote.module.service.TransactionService;
import com.swissquote.module.utils.SQResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController(value = "TransactionController")
@RequestMapping(value = "/api/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping(value = "/cashin",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public SQResponse cashIn(HttpServletRequest request, @RequestBody CashInOutRequest cashInOutRequest) {
        return transactionService.cashIn(cashInOutRequest);
    }

    @PostMapping(value = "/cashout",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public SQResponse cashOut(HttpServletRequest request, @RequestBody CashInOutRequest cashInOutRequest) {
        return transactionService.cashOut(cashInOutRequest);
    }

    @PostMapping(value = "/transfer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public SQResponse transfer(HttpServletRequest request, @RequestBody TransferRequest transferRequest) {
        return transactionService.transfer(transferRequest);
    }

}
