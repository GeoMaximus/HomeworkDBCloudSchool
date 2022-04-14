package com.db.account;

import org.springframework.stereotype.Service;

@Service
public class AccountService {
    public String generateIBAN(Account account) {
        return "001" + account.getCurrency() + account.getId() / 4 + "123456789";
    }
}
