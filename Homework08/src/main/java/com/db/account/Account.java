package com.db.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue
    public long id;
    private String IBAN;
    @Column(name = "user_id")
    private int userId;
    private String countryCode;
    private String bankCode;
    private Currency currency;
    private double balance;


    public void setBalance(double balance) {
        this.balance = balance;
    }

}
