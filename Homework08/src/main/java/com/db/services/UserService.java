package com.db.services;

import com.db.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public static String getInitials(User user) {
        return user.getFirstName().substring(0, 1) + user.getLastName().substring(0, 1);
    }

    public static String userInitials(User user) {
        int index = user.getFirstName().indexOf(' ');
        String initials = "";
        if (index != 0) {
            initials = user.getFirstName().substring(0, 1) + user.getFirstName().substring(index + 1, index + 2);
        }
        return initials;
    }
}
