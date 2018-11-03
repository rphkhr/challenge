package com.swissquote.module.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class CreateAccountRequest {

    private String personId;

    private BigDecimal balance;

    private String currency;

    public CreateAccountRequest() {
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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

        if (!(o instanceof CreateAccountRequest)) return false;

        CreateAccountRequest that = (CreateAccountRequest) o;

        return new EqualsBuilder()
                .append(personId, that.personId)
                .append(balance, that.balance)
                .append(currency, that.currency)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(personId)
                .append(balance)
                .append(currency)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("personId", personId)
                .append("balance", balance)
                .append("currency", currency)
                .toString();
    }
}
