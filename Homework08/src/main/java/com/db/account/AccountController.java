package com.db.account;

import com.db.config.exceptions.ExistingAccountException;
import com.db.config.exceptions.InvalidUserException;
import com.db.transferMoney.Transaction;
import com.db.transferMoney.TransferMoneyService;
import com.db.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    TransferMoneyService transferMoneyServiceInternal;

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

//    @PutMapping("/transfer")
//    public ResponseEntity<?> transfer(@RequestParam int from, @RequestParam int to) {
//        Transaction transaction = new Transaction(accountRepository.findById(from).get(), accountRepository.findById(to).get(), 100);
//        transferMoneyServiceInternal.transferMoney(transaction);
//        return new ResponseEntity<>(HttpStatus.OK);
//
//    }
}
