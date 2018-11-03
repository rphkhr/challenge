package com.swissquote.module.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity(name = "transactions")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {

    @Id
    @GeneratedValue
    private String id;

    private String transactionType;

    private BigDecimal amount;
    private String currency;

    @CreationTimestamp
    private Timestamp created;

    @ManyToOne
    @JoinColumn(name = "source_account")
    @JsonBackReference
    private Account sourceAccount;

    @ManyToOne
    @JoinColumn(name = "target_account")
    @JsonBackReference
    private Account targetAccount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
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

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("transactionType", transactionType)
                .append("amount", amount)
                .append("currency", currency)
                .append("created", created)
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
                .append(created, that.created)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(transactionType)
                .append(amount)
                .append(currency)
                .append(created)
                .toHashCode();
    }
}
