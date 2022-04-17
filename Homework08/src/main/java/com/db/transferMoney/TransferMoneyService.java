package com.db.transferMoney;

import com.db.account.Account;
import org.springframework.stereotype.Service;

@Service
public interface TransferMoneyService {
    void transferMoney(Transaction transaction);
}
