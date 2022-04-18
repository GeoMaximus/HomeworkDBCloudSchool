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
    public Transaction transferMoney(TransactionRequest transactionRequest) {
        Optional<Account> fromAccount = accountRepository.findById(transactionRequest.getFromAccountId());
        if (fromAccount.isEmpty()) {
            return null;
        }
        Optional<Account> destinationAccount = accountRepository.findById(transactionRequest.getDestinationAccountId());
        if(destinationAccount.isEmpty()){
            return null;
        }
        if(transactionRequest.getAmount() > )


        return null;
    }
}
