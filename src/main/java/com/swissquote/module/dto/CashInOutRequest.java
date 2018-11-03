package com.swissquote.module.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class CashInOutRequest {

    private String accountId;
    private BigDecimal amount;
    private String currency;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("accountId", accountId)
                .append("amount", amount)
                .append("currency", currency)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof CashInOutRequest)) return false;

        CashInOutRequest that = (CashInOutRequest) o;

        return new EqualsBuilder()
                .append(accountId, that.accountId)
                .append(amount, that.amount)
                .append(currency, that.currency)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(accountId)
                .append(amount)
                .append(currency)
                .toHashCode();
    }
}
