package com.greenfox.jasper.controllers;

import com.greenfox.jasper.domain.AccountInfo;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.servlet.account.AccountResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserDetailsController {

    private AccountResolver accountResolver;

    @Autowired
    public UserDetailsController(AccountResolver accountResolver) {
        Assert.notNull(accountResolver);
        this.accountResolver = accountResolver;
    }

    @RequestMapping(value = "/userdetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public AccountInfo info(HttpServletRequest req) {
        // must be logged in to get here per Spring Security config
        Account account = accountResolver.getAccount(req);

        return new AccountInfo(account.getEmail(), account.getFullName(), account.getHref());
    }

}
