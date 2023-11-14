package com.example.booking.config;

import com.example.booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CurrentUser {

    private final UserService userService;

    @Autowired
    public CurrentUser(UserService userService) {
        this.userService = userService;
    }

    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public Long getUserId() {
        String currentUsername = getUsername();
        return userService.getUserIdByUsername(currentUsername);
    }

    public Set<String> getRoles() {
        String currentUsername = getUsername();
        return userService.getUserRolesByUsername(currentUsername);
    }



}
