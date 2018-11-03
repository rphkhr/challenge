package com.swissquote.module.repository;

import com.swissquote.module.entity.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface AccountRepository extends CrudRepository<Account, String> {


    @Transactional
    @Modifying
    @Query("UPDATE com.swissquote.module.entity.Account AS acc SET acc.balance =:balance where acc.id=:id")
    Integer setNewAccountBalance(@Param("id") String accountId, @Param("balance") BigDecimal accountBalance);
}
