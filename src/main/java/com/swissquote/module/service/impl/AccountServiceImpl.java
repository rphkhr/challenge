package com.swissquote.module.service.impl;

import com.swissquote.module.dto.CreateAccountRequest;
import com.swissquote.module.entity.Account;
import com.swissquote.module.entity.Person;
import com.swissquote.module.repository.AccountRepository;
import com.swissquote.module.repository.PersonRepository;
import com.swissquote.module.service.AccountService;
import com.swissquote.module.utils.SQResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public SQResponse createAccount(CreateAccountRequest createAccountRequest) {
        String personId = createAccountRequest.getPersonId();

        if (personId == null) return new SQResponse("PersonID cannot be null");
        if (createAccountRequest.getCurrency() == null) return new SQResponse("Currency cannot be null");

        Person person = personRepository.findOne(personId);

        if (person != null) {
            Account account = new Account();
            account.setPerson(person);
            account.setBalance(createAccountRequest.getBalance());
            account.setCurrency(createAccountRequest.getCurrency());

            accountRepository.save(account);

            return new SQResponse(account);
        }

        return new SQResponse("Could not find personID in DB");
    }

    @Override
    public SQResponse getAccount(String accountId) {
        Account account = accountRepository.findOne(accountId);
        if (account == null) {
            return new SQResponse("Could not find account in DB");
        }
        return new SQResponse(accountId);
    }
}
