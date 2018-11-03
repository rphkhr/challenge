package com.swissquote.module.dto;

import java.math.BigDecimal;

public class TransferRequest {

    private String sourceAccountId;
    private String destinationAccountId;
    private BigDecimal amount;
    private String currency;
}
