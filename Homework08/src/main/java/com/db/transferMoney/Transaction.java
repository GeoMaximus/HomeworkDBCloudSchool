package com.db.transferMoney;

import com.db.account.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private String fromIBAN;
    private String destinationIBAN;
    private double transactionAmount;

}
