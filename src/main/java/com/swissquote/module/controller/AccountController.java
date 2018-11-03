package com.swissquote.module.controller;

import com.swissquote.module.dto.CreateAccountRequest;
import com.swissquote.module.entity.Account;
import com.swissquote.module.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController(value = "AccountController")
@RequestMapping(value = "/api/v1/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Account createAccount(HttpServletRequest request, @RequestBody CreateAccountRequest createAccountRequest) {
        return accountService.createAccount(createAccountRequest);
    }

    @GetMapping(value = "/get/{id}")
    public Account retrievePerson(HttpServletRequest request, HttpServletResponse response,
                                 @PathVariable(value = "id") String accountId) {
        return accountService.getAccount(accountId);
    }
}
