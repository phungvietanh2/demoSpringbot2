package com.example.booking.Controller;

import com.example.booking.config.CurrentUser;
import com.example.booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class test {

    @Autowired
    private UserService userService;

    @Autowired
    private CurrentUser currentUser;

    @GetMapping("/test")
    private String shop(Model model){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUsername = authentication.getName();
//        Long userId = userService.getUserIdByUsername(currentUsername);
//        model.addAttribute("username", currentUsername);
//        model.addAttribute("userId", userId);
//
//        Set<String> roles = userService.getUserRolesByUsername(currentUsername);
//        model.addAttribute("roles", roles);
//
        model.addAttribute("username", currentUser.getUsername());
        model.addAttribute("userId", currentUser.getUserId());
        model.addAttribute("roles", currentUser.getRoles());
        return "test";
    }
}
