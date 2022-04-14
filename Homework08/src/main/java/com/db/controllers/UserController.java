package com.db.controllers;

import com.db.exceptions.InvalidUserException;
import com.db.models.User;
import com.db.repositories.UserRepository;
import com.db.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @PostMapping("create")
    public void createUser(@RequestBody User user) {
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

    //All users’ initials
    @GetMapping("/initials")
    public List<String> getInitials() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().map(user -> user.getFirstName().substring(0, 1) + user.getLastName().substring(0, 1)).collect(Collectors.toList());
    }

    //Number of users that signed up with gmail
    @GetMapping("/gmail")
    public long getGmailUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().filter(user -> user.getEmail().contains("@gmail")).count();
    }

    //List of each last name that any user has
    @GetMapping("/lastNames")
    public void getLastNames() {
        List<User> users = (List<User>) userRepository.findAll();
    }

    //A string that contains every user’s first name initial
    @GetMapping("/everyInitial")
    public List<String> getFirstNameInitials() {
        List<User> users = (List<User>) userRepository.findAll();
        List<String> initials = new ArrayList<>();
//        return users.stream().map(user -> user.getLastName().substring(0, 1)).collect(Collectors.joining(", ", "[", "]"));
        for (User user : users) {
           initials.add(userService.userInitials(user));
        }
        return initials;
    }

    //The number of users whose first name contains an ‘a’
    @GetMapping("/a")
    public long containsA() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().filter(user -> user.getFirstName().contains("a") && user.getAge() < 20).count();
    }

}
