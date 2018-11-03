package com.swissquote.module.service;

import com.swissquote.module.dto.CreateAccountRequest;
import com.swissquote.module.entity.Account;
import com.swissquote.module.entity.Person;

public interface AccountService {

    Person createAccount(CreateAccountRequest createAccountRequest);

    Account getAccount(String accountId);
}
