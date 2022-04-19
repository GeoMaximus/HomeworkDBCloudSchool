package com.db.transferMoney;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class Transaction {
    private String fromIBAN;
    private String destinationIBAN;
    private double transactionAmount;

}
