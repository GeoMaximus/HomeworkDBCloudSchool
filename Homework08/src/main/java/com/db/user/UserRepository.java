package com.db.user;

import com.db.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @Query(value = "SELECT * FROM users WHERE first_name like %?1%", nativeQuery = true)
    List<User> findByFirstName(String name);

//    boolean existsByFirstname(String firstname);
//
//    boolean existByLastname(String lastname);
}
