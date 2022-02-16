package org.study.periodicals.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.study.periodicals.model.Role;
import org.study.periodicals.model.User;
import org.study.periodicals.repository.impl.DefaultUsersRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class AuthController {

    DefaultUsersRepository usersRepository;

    PasswordEncoder passwordEncoder;

    public AuthController(DefaultUsersRepository usersRepository, PasswordEncoder passwordEncoder) {

        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/userRegister")
    public String registration() {
        return "userRegister";
    }

    @PostMapping("/userRegister")
    public String userRegistration(@ModelAttribute("user") User user) throws ServletException {
        user.setRegister(new Date());
        user.setStatus(true);
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        usersRepository.createUser(user);
        return "userPage";
    }
}
