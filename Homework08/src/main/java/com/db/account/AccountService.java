package com.db.account;

import com.db.transferMoney.TransferMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    TransferMoneyService transferMoneyService;
    public AccountService(@Autowired List<TransferMoneyService> transferMoneyServiceList, @Autowired @Qualifier("transferMoneyExternal") TransferMoneyService transferMoneyService) {
        this.transferMoneyService = transferMoneyService;
    }
    public String generateIBAN(Account account) {
        return "001" + account.getCurrency() + account.getId() / 4 + "123456789";
    }
}
