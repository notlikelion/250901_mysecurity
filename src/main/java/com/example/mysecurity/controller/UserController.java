package com.example.mysecurity.controller;

import com.example.mysecurity.service.UserAccountService;
import com.example.mysecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String myPage(Authentication authentication, Model model) {
        // authentication.getName() // username
        // 디폴트, 커스텀 로그인 폼 -> username, password
        // -> UserDetailsService(Interface) -> login
        // 내일 시각자료를 가져오겠음.
        model.addAttribute("username", authentication.getName());
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
