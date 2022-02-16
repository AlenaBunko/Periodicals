package org.study.periodicals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.study.periodicals.model.Subscription;
import org.study.periodicals.model.User;
import org.study.periodicals.repository.impl.DefaultEditionsRepository;
import org.study.periodicals.repository.impl.DefaultUsersRepository;

import java.util.Date;

@Controller
public class SubscriptionController {

    DefaultUsersRepository usersRepository;

    public SubscriptionController(DefaultUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/personal/addFormSubscription")
    public String formSubscription() {
        return "addSubscriptions";
    }

    @PostMapping("/personal/addFormSubscription")
    public String addSubscription(@ModelAttribute Subscription subscription) {

        usersRepository.addSubscription(subscription);

        return "mySubscriptions";
    }

}
