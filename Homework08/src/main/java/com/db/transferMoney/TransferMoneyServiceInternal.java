package com.db.transferMoney;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("external")
@Service
public class TransferMoneyServiceInternal implements TransferMoneyService{
    @Override
    public void transferMoney() {

    }
}
