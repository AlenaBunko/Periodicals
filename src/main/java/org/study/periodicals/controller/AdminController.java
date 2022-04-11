package org.study.periodicals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.study.periodicals.model.User;
import org.study.periodicals.service.AdminService;

import java.util.List;

@Controller
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/adminPage/allUsers")
    public ModelAndView tableOfUsers(ModelAndView modelAndView) {
        List<User> users = adminService.findAllUsers();
        modelAndView.setViewName("tableUsers");
        modelAndView.addObject("users", users);
        return modelAndView;
    }


}