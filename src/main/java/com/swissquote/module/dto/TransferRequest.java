package com.swissquote.module.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class TransferRequest {

    private String sourceAccountId;
    private String destinationAccountId;
    private BigDecimal amount;
    private String currency;

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(String sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public String getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(String destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
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
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof TransferRequest)) return false;

        TransferRequest that = (TransferRequest) o;

        return new EqualsBuilder()
                .append(sourceAccountId, that.sourceAccountId)
                .append(destinationAccountId, that.destinationAccountId)
                .append(amount, that.amount)
                .append(currency, that.currency)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(sourceAccountId)
                .append(destinationAccountId)
                .append(amount)
                .append(currency)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("sourceAccountId", sourceAccountId)
                .append("destinationAccountId", destinationAccountId)
                .append("amount", amount)
                .append("currency", currency)
                .toString();
    }
}
