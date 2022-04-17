package com.db.transferMoney;

import com.db.account.Account;
import com.db.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
public class TransferMoneyServiceInternal implements TransferMoneyService{

    @Autowired
    AccountRepository accountRepository;
    @Override
    public void transferMoney(Transaction transaction) {
        if(accountRepository.existsByIBAN(transaction.getFromAccount().getIBAN()) && transaction.getFromAccount().getBalance() > 0) {
            transaction.getFromAccount().setBalance(transaction.getFromAccount().getBalance() - transaction.getTransactionAmount());
            transaction.getDestinationAccount().setBalance(transaction.getDestinationAccount().getBalance() + transaction.getTransactionAmount());
        }
    }
}
