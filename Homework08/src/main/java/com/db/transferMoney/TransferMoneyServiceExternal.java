package com.db.transferMoney;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("internal")
@Service
public class TransferMoneyServiceExternal implements TransferMoneyService{
    @Override
    public void transferMoney() {

    }
}
