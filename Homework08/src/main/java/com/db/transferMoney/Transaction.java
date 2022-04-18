package com.db.transferMoney;

import com.db.account.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="transfers")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private Account fromAccount;
    @ManyToOne
    private Account destinationAccount;
    private double transactionAmount;

}
