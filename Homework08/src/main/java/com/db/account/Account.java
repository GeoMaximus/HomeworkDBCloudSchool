package com.db.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
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
}
