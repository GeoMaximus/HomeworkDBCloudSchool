package com.db.account;

import com.db.config.exceptions.ExistingAccountException;
import com.db.config.exceptions.InvalidUserException;
import com.db.config.exceptions.TransactionException;
import com.db.transferMoney.Transaction;
import com.db.transferMoney.TransferMoneyService;
import com.db.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
        } else if (accountRepository.existsByIBAN(account.getIBAN())) {
            throw new ExistingAccountException("The account already exists");
        } else {
            account.setIBAN(accountService.generateIBAN(account));
            accountRepository.save(account);
            return new ResponseEntity<>(account, HttpStatus.CREATED);
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferMoney(@RequestBody Transaction transaction) throws TransactionException {
        if (accountRepository.existsByIBAN(transaction.getFromIBAN()) && accountRepository.existsByIBAN(transaction.getDestinationIBAN())) {
            if (transaction.getFromIBAN().contains("DEUT") && transaction.getDestinationIBAN().contains("DEUT")) {
                transferMoneyServiceInternal.transferMoney(transaction);
                return new ResponseEntity<>(HttpStatus.OK);
            } else if (transaction.getFromIBAN().contains("DEUT") && !transaction.getDestinationIBAN().contains("DEUT")) {
                transferMoneyServiceExternal.transferMoney(transaction);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                System.out.println("Transaction could not be processed");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
