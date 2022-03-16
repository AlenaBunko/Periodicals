package org.study.periodicals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.study.periodicals.model.User;
import org.study.periodicals.repository.interfaces.UsersRepository;
import org.study.periodicals.service.AdminService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/adminPage/allUsers")
    public String tableOfUsers(Model model) {
        List<User> allUsers = adminService.findAllUsers();
        model.addAttribute("users", allUsers);
        return "tableUsers";
    }


}
