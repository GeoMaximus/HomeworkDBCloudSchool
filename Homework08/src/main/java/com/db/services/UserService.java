package com.db.services;

import com.db.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public static String getInitials(User user) {
        return userInitials(user) + user.getLastName().substring(0, 1);
    }

    public static String userInitials(User user) {
        int index = user.getFirstName().indexOf(' ');
        if (user.getFirstName().contains(" ")) {
            return user.getFirstName().substring(0, 1) + user.getFirstName().substring(index + 1, index + 2);
        } else
            return user.getFirstName().substring(0, 1);
    }

    public static boolean checkUser(User user) {
        return (user.getFirstName().contains("a") || user.getFirstName().contains("A")) && user.getAge() < 20;
    }

    public static String firstNameInitial(User user) {
        return user.getFirstName().substring(0, 1);
    }
}
