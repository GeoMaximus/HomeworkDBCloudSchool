package com.db.transferMoney;

import com.db.config.exceptions.TransactionException;
import org.springframework.stereotype.Service;

@Service
public interface TransferMoneyService {
    void transferMoney(Transaction transaction) throws TransactionException;
}
