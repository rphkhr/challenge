package com.swissquote.module.repository;

import com.swissquote.module.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, String> {
}
