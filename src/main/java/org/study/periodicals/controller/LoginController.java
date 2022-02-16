package org.study.periodicals.controller;

import org.springframework.stereotype.Controller;
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
        public String index(){
            return "index";
        }

    @GetMapping("/loginPage")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(@RequestParam(name = "login") String login, @RequestParam(name = "password") String password, HttpServletRequest request) {
        if (!userAuthorizationService.isUserAuthenticated(login, password)) {
            return "redirect:loginPage";
        }
        userAuthorizationService.saveSession(request.getSession(true).getId(), login);
        return "redirect:personal/userPage";
    }

    @GetMapping("/personal/userPage")
    public String userPage() {
        return "userPage";
    }


}
