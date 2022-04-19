package com.db.transferMoney;

import com.db.account.Account;
import com.db.account.AccountRepository;
import com.db.config.exceptions.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("internal")
public class TransferMoneyServiceInternal implements TransferMoneyService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void transferMoney(Transaction transaction) throws TransactionException {
        if (accountRepository.existsByIBAN(transaction.getFromIBAN()) && accountRepository.existsByIBAN(transaction.getDestinationIBAN())) {
            Account fromAccount = accountRepository.findByIBAN(transaction.getFromIBAN());
            Account destinationAccount = accountRepository.findByIBAN(transaction.getDestinationIBAN());
            if (fromAccount.getBalance() >= transaction.getTransactionAmount()) {
                updateBalance(transaction, fromAccount, destinationAccount);
                accountRepository.save(fromAccount);
                accountRepository.save(destinationAccount);
            } else {
                throw new TransactionException("Not enough credits to process the transaction");
            }
        } else {
            throw new TransactionException("The account does not exists");
        }
    }

    private void updateBalance(Transaction transaction, Account fromAccount, Account destinationAccount) {
        double fromAccountFinalBalance = fromAccount.getBalance() - transaction.getTransactionAmount();
        double destinationAccountFinalBalance = destinationAccount.getBalance() + transaction.getTransactionAmount();
        fromAccount.setBalance(fromAccountFinalBalance);
        destinationAccount.setBalance(destinationAccountFinalBalance);
    }
}
