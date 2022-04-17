package com.db.transferMoney;

import com.db.account.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private Account fromAccount;
    private Account destinationAccount;
    private double transactionAmount;

}
