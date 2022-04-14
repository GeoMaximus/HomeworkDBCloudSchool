package com.db.user;

import com.db.account.Account;
import com.db.account.AccountRepository;
import com.db.config.exceptions.InvalidUserException;
import com.db.config.exceptions.UserConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    AccountRepository accountRepository;

    @PostMapping()
    public void createUser(@RequestBody User user) throws InvalidUserException, UserConflictException {
        if (!userService.isUserValid(user)) {
            throw new InvalidUserException("User details are not valid");
        }
//        if (userRepository.existsByFirstname(user.getFirstName()) && userRepository.existByLastname(user.getLastName())) {
//            throw new UserConflictException("Another user with the same name already exists");
//        }
        userRepository.save(user);
    }

    @GetMapping("/{id}")
    public User getUsersById(@PathVariable int id) throws InvalidUserException {
        return userRepository.findById(id)
                .orElseThrow(() -> new InvalidUserException("The user does not exist!"));
    }

    @GetMapping("/search")
    public List<User> searchUserByFirstName(@RequestParam String name) {
        return userRepository.findByFirstName(name);
    }

    @GetMapping("/{userId}")
    public List<Account> getAccountsByUserId(@PathVariable int userId) throws InvalidUserException {
        if (!userRepository.existsById(userId)) {
            throw new InvalidUserException("The user does not exist");
        } else {
            return new ArrayList<>(accountRepository.findByUserId(userId));
        }
    }

    //returns a list with the initials of all users
    @GetMapping("/initials")
    public List<String> getInitials() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().map(UserService::getAllInitials).collect(Collectors.toList());
    }

    //Number of users that signed up with gmail
    @GetMapping("/gmail")
    public long getGmailUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().filter(user -> user.getEmail().contains("@gmail")).count();
    }

    //List of each last name that any user has
    @GetMapping("/lastNames")
    public List<String> getLastNames() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().map(User::getLastName).distinct().collect(Collectors.toList());
    }

    //A string that contains every user’s first name initial
    @GetMapping("/everyInitial")
    public String getFirstNameInitials() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().map(user -> user.getFirstName().substring(0, 1)).reduce((str1, str2) -> str1 + str2).orElse("");
    }

    //The number of users whose first name contains an ‘a’ (both lower and upper)
    @GetMapping("/a")
    public long containsA() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().filter(UserService::checkUser).count();
    }

}
