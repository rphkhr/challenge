package com.swissquote.module.entity;

import com.swissquote.module.utils.TransactionType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue
    private String id;

    private TransactionType transactionType;

    private BigDecimal amount;
    private String currency;
    private Account sourceAccount;
    private Account targetAccount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
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

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Account getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(Account targetAccount) {
        this.targetAccount = targetAccount;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("transactionType", transactionType)
                .append("amount", amount)
                .append("currency", currency)
                .append("sourceAccount", sourceAccount)
                .append("targetAccount", targetAccount)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Transaction)) return false;

        Transaction that = (Transaction) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(transactionType, that.transactionType)
                .append(amount, that.amount)
                .append(currency, that.currency)
                .append(sourceAccount, that.sourceAccount)
                .append(targetAccount, that.targetAccount)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(transactionType)
                .append(amount)
                .append(currency)
                .append(sourceAccount)
                .append(targetAccount)
                .toHashCode();
    }
}
