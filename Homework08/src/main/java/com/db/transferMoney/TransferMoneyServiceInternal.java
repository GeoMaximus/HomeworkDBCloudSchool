package com.db.transferMoney;

import com.db.account.Account;
import com.db.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("internal")
public class TransferMoneyServiceInternal implements TransferMoneyService {

    @Autowired
    AccountRepository accountRepository;


    //create a transfer table
    //save all transfers in that table
    //update with a query the accounts
    @Override
    public void transferMoney(String from, String destination, double amount) {
        Account fromAccount = accountRepository.findByIBAN(from);
        Account destinationAccount = accountRepository.findByIBAN(destination);
        double fromAccountFinalBalance;
        double destinationAccountFinalBalance;

        if (fromAccount != null && destinationAccount != null) {
            if (fromAccount.getBalance() - amount >= 0) {
                fromAccountFinalBalance = fromAccount.getBalance() - amount;
                destinationAccountFinalBalance = destinationAccount.getBalance() + amount;

                fromAccount.setBalance(fromAccountFinalBalance);
                destinationAccount.setBalance(destinationAccountFinalBalance);
                accountRepository.save(fromAccount);
                accountRepository.save(destinationAccount);
            } else {
                System.out.println("not enough credits");
            }
        } else {
            System.out.println("the account does not exist");
        }
    }
}
