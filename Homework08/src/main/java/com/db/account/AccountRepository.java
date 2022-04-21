package com.db.account;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Integer> {
    List<Account> findByUserId(int userId);
    Account findByIBAN(String iban);

    boolean existsByIBAN(String iban);
}
