package org.study.periodicals.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.study.periodicals.model.User;
import org.study.periodicals.repository.impl.DefaultUsersRepository;

import java.security.Principal;

@Controller
@SessionAttributes(value = {LoginController.USER})
public class LoginController {

    static final String USER = "user";

    DefaultUsersRepository usersRepository;

    public LoginController(DefaultUsersRepository usersRepository) {
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

    @GetMapping("/userPage")
    public ModelAndView userPage(Principal principal, ModelAndView view) {
        String login = ((User)(((UsernamePasswordAuthenticationToken) principal).getPrincipal())).getLogin();
        if (login != null) {
            try {
                User user = usersRepository.findByLogin(login);
                view.setViewName("userPage");
                view.addObject(USER, user);
            } catch (Exception e) {
                view.addObject("error", e.getMessage());
                view.setViewName("login");
            }
        } else {
            view.setViewName("login");
        }
        return view;
    }

}
