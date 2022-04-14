package com.db.controllers;

import com.db.exceptions.InvalidUserException;
import com.db.models.User;
import com.db.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

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
        for (User u : users) {
            List<String> firstNames = Collections.singletonList(u.getFirstName().substring(0, 1));
            List<String> lastNames = Collections.singletonList(u.getLastName().substring(0, 1));
            List<String> initials = null;
            initials.add(firstNames.get(1) + lastNames.get(1));
        }
        List<String> initials = null;
        return initials;
    }

    //Number of users that signed up with gmail
    @GetMapping("/gmail")
    public void getGmailUsers() {

    }

    //List of each last name that any user has
    @GetMapping("/lastNames")
    public void getLastNames() {

    }

    //A string that contains every user’s first name initial
    @GetMapping("/everyInitial")
    public void getFirstNameInitials() {

    }

    //The number of users whose first name contains an ‘a’
    @GetMapping("/a")
    public void containsA() {

    }

}
