package com.db.account;

import com.db.config.exceptions.ExistingAccountException;
import com.db.config.exceptions.InvalidUserException;
import com.db.transferMoney.Transaction;
import com.db.transferMoney.TransferMoneyService;
import com.db.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    @Qualifier("internal")
    TransferMoneyService transferMoneyServiceInternal;
    @Autowired
    @Qualifier("external")
    TransferMoneyService transferMoneyServiceExternal;

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) throws InvalidUserException, ExistingAccountException {
        if (!userRepository.existsById(account.getUserId())) {
            throw new InvalidUserException("The user does not exist");
        } else if (accountRepository.existsById(account.getId())) {
            throw new ExistingAccountException("The account already exists");
        } else {
            account.setIBAN(accountService.generateIBAN(account));
            accountRepository.save(account);
            return new ResponseEntity<>(account, HttpStatus.CREATED);
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferMoney(@RequestBody Transaction transaction) {
        String fromIban;
        String destinationIban;

        if(transaction!=null && transaction.getFromIBAN() != null && transaction.getDestinationIBAN() != null) {
            fromIban = transaction.getFromIBAN();
            destinationIban = transaction.getDestinationIBAN();
            transferMoneyServiceInternal.transferMoney(fromIban, destinationIban, transaction.getTransactionAmount());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            System.out.println("Transaction could not be processed");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
