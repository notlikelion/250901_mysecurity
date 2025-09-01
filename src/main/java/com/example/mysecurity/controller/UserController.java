package com.example.mysecurity.controller;

import com.example.mysecurity.service.UserAccountService;
import com.example.mysecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/my-page")
    public String myPage() {
        return "myPage";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "registerForm";
    }

    final private UserAccountService userAccountService;

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        userAccountService.register(username, password);
        return "redirect:/";
    }
}
