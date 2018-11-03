package com.swissquote.module.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Entity(name = "persons")
public class Person {

    @Id
    private String id;

    private String fullName;

    @JsonIgnore
    private Date dateOfBirth;

    @CreationTimestamp
    private Timestamp created;

    @Transient
    private Map<String, BigDecimal> accountsBalance;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Account> accounts = new HashSet<>();

    public Person() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Map<String, BigDecimal> getAccountsBalance() {
        return accountsBalance;
    }

    public void setAccountsBalance(Map<String, BigDecimal> accountsBalance) {
        this.accountsBalance = accountsBalance;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        return new EqualsBuilder()
                .append(id, person.id)
                .append(fullName, person.fullName)
                .append(dateOfBirth, person.dateOfBirth)
                .append(created, person.created)
                .append(accountsBalance, person.accountsBalance)
                .append(accounts, person.accounts)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(fullName)
                .append(dateOfBirth)
                .append(created)
                .append(accountsBalance)
                .append(accounts)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("fullName", fullName)
                .append("dateOfBirth", dateOfBirth)
                .append("created", created)
                .append("accountsBalance", accountsBalance)
                .append("accounts", accounts)
                .toString();
    }

    public void calculateBalanceByCurrency() {
        Map<String, BigDecimal> accountsBalances = new HashMap<>();

        Map<String, List<Account>> accountGroupedByCurrency =
                accounts.stream()
                        .collect(Collectors.groupingBy(Account::getCurrency));

        BigDecimal tmp = BigDecimal.ZERO;
        for (Map.Entry<String, List<Account>> entry : accountGroupedByCurrency.entrySet()) {
            for (Account account : entry.getValue()) {
                tmp = tmp.add(account.getBalance());
            }
            accountsBalances.put(entry.getKey(), tmp);
            tmp = BigDecimal.ZERO;
        }

        setAccountsBalance(accountsBalances);

    }
}
