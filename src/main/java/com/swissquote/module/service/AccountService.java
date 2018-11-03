package com.swissquote.module.service;

import com.swissquote.module.dto.CreateAccountRequest;
import com.swissquote.module.utils.SQResponse;

public interface AccountService {

    SQResponse createAccount(CreateAccountRequest createAccountRequest);

    SQResponse getAccount(String accountId);
}
