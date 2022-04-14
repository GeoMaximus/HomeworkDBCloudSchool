package com.db.user;

import com.db.user.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    public boolean validateFirstName(String firstName) {
        return Optional.ofNullable(firstName).filter(n -> n.length() > 3).isPresent();
    }

    public boolean validateLastName(String lastName) {
        return Optional.ofNullable(lastName).filter(n -> n.length() > 3).isPresent();
    }

    public boolean validateAge(int age) {
        return age > 18;
    }

    public boolean validateEmail(String email) {
        return email.contains("@gmail") || email.contains("@yahoo") || email.contains("@mail");
    }

    public boolean validatePassword(String password) {
        return password.length() > 5 && password.length() < 30;
    }

    public boolean isUserValid(User user) {
        return validateFirstName(user.getFirstName()) && validateLastName(user.getLastName()) && validateAge(user.getAge())
                && validateEmail(user.getEmail()) && validatePassword(user.getPassword());
    }

    public static String getAllInitials(User user) {
        return getFirstNameInitials(user) + user.getLastName().substring(0, 1);
    }

    public static String getFirstNameInitials(User user) {
        int index = user.getFirstName().indexOf(' ');
        if (user.getFirstName().contains(" ")) {
            return user.getFirstName().substring(0, 1) + user.getFirstName().substring(index + 1, index + 2);
        } else
            return user.getFirstName().substring(0, 1);
    }

    public static boolean checkUser(User user) {
        return (user.getFirstName().contains("a") || user.getFirstName().contains("A")) && user.getAge() < 20;
    }
}
