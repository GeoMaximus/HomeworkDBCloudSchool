package com.db.transferMoney;

import com.db.account.Account;
import com.db.account.AccountRepository;
import com.db.config.exceptions.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("external")
public class TransferMoneyServiceExternal implements TransferMoneyService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public void transferMoney(Transaction transaction) throws TransactionException {
        if (accountRepository.existsByIBAN(transaction.getFromIBAN())) {
            Account fromAccount = accountRepository.findByIBAN(transaction.getFromIBAN());
            if (fromAccount.getBalance() >= transaction.getTransactionAmount()) {
                double accountFinalBalance = fromAccount.getBalance() - transaction.getTransactionAmount();
                fromAccount.setBalance(accountFinalBalance);
                accountRepository.save(fromAccount);
            } else {
                throw new TransactionException("Not enough credits to process the transaction");
            }
        } else {
            throw new TransactionException("The account does not exists");
        }
    }
}

