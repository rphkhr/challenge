package com.swissquote.module.service;

import com.swissquote.module.dto.CreateAccountRequest;
import com.swissquote.module.entity.Account;

public interface AccountService {

    Account createAccount(CreateAccountRequest createAccountRequest);

    Account getAccount(String accountId);
}
