package org.study.periodicals.controller;

import org.h2.engine.Session;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.study.periodicals.model.User;
import org.study.periodicals.service.UserAuthorizationService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class LoginController {

    private UserAuthorizationService userAuthorizationService;

    public LoginController(UserAuthorizationService userAuthorizationService) {
        this.userAuthorizationService = userAuthorizationService;
    }

    @GetMapping("/loginPage")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(@RequestParam String login, @RequestParam String password) {
        if (!userAuthorizationService.isUserAuthenticated(login, password)) {
            return "redirect:loginPage";
        }

        return "redirect:/personal/userPage";
    }

    @GetMapping("/personal/userPage")
    public ModelAndView userPage(@RequestParam(required = false) String login, @RequestParam(required = false) String password, HttpSession session, ModelAndView view) {

        HashMap<String, Integer> authUser = new HashMap<>();
        String keyUser = String.valueOf(authUser.get(userAuthorizationService.isUserAuthorized(String.valueOf(session.getId()))));

        Boolean doUser = userAuthorizationService.isUserAuthenticated(login, password);
        if (doUser == true) {
            try {
                User user = new User();
                Integer valueUser = user.getId();
                authUser.put(keyUser, valueUser);
                view.setViewName("userPage");
                view.addObject(user);
            } catch (Exception e) {
                view.addObject("error", e.getMessage());
                view.setViewName("loginPage");
            }
        } else {
            view.setViewName("loginPage");
        }
        return view;
    }

}
