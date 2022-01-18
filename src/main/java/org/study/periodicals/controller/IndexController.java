package org.study.periodicals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.study.periodicals.model.User;
import org.study.periodicals.repository.impl.DefaultUsersRepository;

import javax.servlet.http.HttpServlet;

@Controller
public class IndexController {

    DefaultUsersRepository usersRepository;

    public IndexController(DefaultUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String userPage(@RequestParam String login, Model model) {
        User user = usersRepository.findByLogin(login);
        if (user != null) {
            model.addAttribute("password", user.getPassword());
            return "redirect:/userPage";
        }
        else {
            model.addAttribute("error", "User not found");
        }
        return "login";
    }


}
