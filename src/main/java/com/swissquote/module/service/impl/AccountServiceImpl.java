package com.swissquote.module.service.impl;

import com.swissquote.module.dto.CreateAccountRequest;
import com.swissquote.module.entity.Account;
import com.swissquote.module.entity.Person;
import com.swissquote.module.repository.AccountRepository;
import com.swissquote.module.repository.PersonRepository;
import com.swissquote.module.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Account createAccount(CreateAccountRequest createAccountRequest) {

        Person person = personRepository.findOne(createAccountRequest.getPersonId());

        if (person != null) {
            Account account = new Account();
            account.setPerson(person);
            account.setBalance(createAccountRequest.getBalance());
            account.setCurrency(createAccountRequest.getCurrency());

            accountRepository.save(account);

            return account;

        }

        return null;
    }

    @Override
    public Account getAccount(String accountId) {
        return accountRepository.findOne(accountId);
    }
}
