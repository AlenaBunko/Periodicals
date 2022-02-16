package org.study.periodicals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.study.periodicals.service.UserAuthorizationService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogoutController {

    UserAuthorizationService userAuthorizationService;

    public LogoutController(UserAuthorizationService userAuthorizationService) {
        this.userAuthorizationService = userAuthorizationService;
    }

    @PostMapping("/logout")
    public String logoutUser(HttpServletRequest request) {

        userAuthorizationService.deleteSession(request.getSession().getId());

        return "redirect:loginPage";
    }

}


