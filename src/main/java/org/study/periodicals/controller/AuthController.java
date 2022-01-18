package org.study.periodicals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.study.periodicals.model.User;
import org.study.periodicals.repository.impl.DefaultUsersRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {

    DefaultUsersRepository usersRepository;

    public AuthController(DefaultUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/userRegister")
    public String registration() {
        return "userRegister";
    }

    @PostMapping("/userRegister")
    public String userRegistration(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
        usersRepository.createUser(user);
//        request.login(user.getLogin(), user.getPassword());
        return "redirect:/userPage";
    }
}
