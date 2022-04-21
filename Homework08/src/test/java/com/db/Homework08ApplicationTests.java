package com.db;

import com.db.account.Account;
import com.db.account.AccountRepository;
import com.db.user.User;
import com.db.user.UserRepository;
import com.db.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = UserRepository.class)
@ExtendWith(MockitoExtension.class)
public class Homework08ApplicationTests {
    @MockBean
    UserRepository mockUserRepository;
    @MockBean
    AccountRepository mockAccountRepository;
    @MockBean
    UserService userService;

    @Test
    public void shouldGetFirstNameById() {
        Optional<User> userOp = Optional.of(new User());
        userOp.get().setFirstName("Marian");
        Mockito.when(mockUserRepository.findById(1)).thenReturn(userOp);
        assertEquals("Marian", mockUserRepository.findById(1).get().getFirstName());
    }


    @Test
    public void shouldGetAgeById() {
        Optional<User> userOp = Optional.of(new User());
        userOp.get().setAge(21);
        Mockito.when(mockUserRepository.findById(1)).thenReturn(userOp);
        assertEquals(21, mockUserRepository.findById(1).get().getAge());
    }

    @Test
    public void shouldGetAccountByUserId() {
        List<Account> accountList = List.of(new Account());
        accountList.get(0).setUserId(1);
        Mockito.when(mockAccountRepository.findByUserId(1)).thenReturn(accountList);
        assertEquals(accountList, mockAccountRepository.findByUserId(1));
    }

    @Test
    public void shouldGetAccountByIBAN() {
        Account account = new Account();
        account.setIBAN("IBAN1");
        Mockito.when(mockAccountRepository.findByIBAN("IBAN1")).thenReturn(account);
        assertEquals(account, mockAccountRepository.findByIBAN("IBAN1"));
    }

    @Test
    public void shouldCheckIfAccountExistsByIban() {
        Account account = new Account();
        account.setIBAN("IBAN1");
        Mockito.when(mockAccountRepository.existsByIBAN("IBAN1")).thenReturn(true);
        assertEquals(true, mockAccountRepository.existsByIBAN("IBAN1"));
    }

    @Test
    public void shouldValidateUserFirstName() {
        Optional<User> user = Optional.of(new User());
        user.get().setFirstName("Marian");
        assertFalse(userService.validateFirstName(user.get().getFirstName()));
    }

    @Test
    public void shouldValidateUserLastName() {
        Optional<User> user = Optional.of(new User());
        user.get().setLastName("Marian");
        assertFalse(userService.validateLastName(user.get().getLastName()));
    }






}
