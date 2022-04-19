package com.db.transferMoney;

import com.db.account.Account;
import com.db.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransferMoneyServiceInternal implements TransferMoneyService {

    @Autowired
    AccountRepository accountRepository;


    //create a transfer table
    //save all transfers in that table
    //update with a query the accounts
    @Override
    public void transferMoney(Account from, Account destination, double amount) {
        if (!accountRepository.existsByIBAN(from.getIBAN()) || !accountRepository.existsByIBAN(destination.getIBAN())) {
            if (from.getBalance() > amount) {
                from.setBalance(from.getBalance() - amount);
                destination.setBalance(destination.getBalance() + amount);
                accountRepository.save(from);
                accountRepository.save(destination);
            } else {
                System.out.println("not enough credits");
            }
        } else {
            System.out.println("the account does not exist");
        }
    }
}
