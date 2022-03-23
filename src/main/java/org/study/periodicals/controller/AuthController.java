package org.study.periodicals.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.study.periodicals.model.User;
import org.study.periodicals.repository.interfaces.UsersRepository;
import java.util.Date;

@Controller
public class AuthController {

    UsersRepository usersRepository;

    PasswordEncoder passwordEncoder;

    public AuthController( UsersRepository usersRepository, PasswordEncoder passwordEncoder) {

        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registrationPage")
    public String registration() {
        return "registrationPage";
    }

    @PostMapping("/registrationPage")
    public String userRegistration(@ModelAttribute("user") User user) throws Exception {
        User userFromDb = usersRepository.findByLogin(user.getUsername());
        if (userFromDb!= null){
            return "registrationPage";
        }
        user.setRegister(new Date());
        user.setStatus(true);
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        usersRepository.createUser(user);
        return "userPage";
    }
}
