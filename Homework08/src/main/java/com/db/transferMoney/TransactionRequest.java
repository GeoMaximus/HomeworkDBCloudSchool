package com.db.transferMoney;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    private int fromAccountId;
    private int destinationAccountId;
    private double amount;
}
