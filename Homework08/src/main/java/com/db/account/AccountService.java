package com.db.account;

import org.springframework.stereotype.Service;

@Service
public class AccountService {
    public String generateIBAN(Account account) {
        String numbers = "0123456789";
        StringBuilder builder = new StringBuilder(8);
        for (int i = 0; i < 7; i++) {
            int index = (int) (numbers.length() * Math.random());
            builder.append(numbers.charAt(index));
        }
        return account.getCountryCode() + account.getUserId() / 32
                + account.getBankCode() + (account.getCurrency()).toString() +
                builder;
    }
}
