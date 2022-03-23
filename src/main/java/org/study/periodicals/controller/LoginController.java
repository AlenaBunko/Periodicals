package org.study.periodicals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.study.periodicals.service.UserAuthorizationService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private UserAuthorizationService userAuthorizationService;

    public LoginController(UserAuthorizationService userAuthorizationService) {
        this.userAuthorizationService = userAuthorizationService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/loginPage")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(@RequestParam(name = "login") String login, @RequestParam(name = "password") String password, HttpServletRequest request, Model model) {
        if (!userAuthorizationService.isUserAuthenticated(login, password)) {
            return "redirect:loginPage";
        } else {
            userAuthorizationService.saveSession(request.getSession(true).getId(), login);
            if (userAuthorizationService.findRole(request.getSession(true).getId()) == 1){
                return "redirect:adminPage";
            }
                return "redirect:personal/userPage";
        }
    }

    @GetMapping("/personal/userPage")
    public String userPage() {
        return "userPage";
    }

    @GetMapping("/adminPage")
    public String adminPage() {
        return "adminPage";
    }


}
