package org.study.periodicals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.study.periodicals.model.Edition;
import org.study.periodicals.model.Subscription;
import org.study.periodicals.model.User;
import org.study.periodicals.repository.impl.DefaultEditionsRepository;
import org.study.periodicals.repository.impl.DefaultUsersRepository;

import java.util.Date;
import java.util.List;

@Controller
public class SubscriptionController {

    DefaultUsersRepository usersRepository;
    DefaultEditionsRepository editionsRepository;

    public SubscriptionController(DefaultUsersRepository usersRepository, DefaultEditionsRepository editionsRepository) {
        this.usersRepository = usersRepository;
        this.editionsRepository = editionsRepository;
    }

    @GetMapping("/personal/addFormSubscription")
    public String formSubscription() {
        return "addSubscriptions";
    }

    @PostMapping("/personal/addFormSubscription")
    public String addSubscription(@ModelAttribute Subscription subscription, @ModelAttribute Edition edition) {
//         if (editionsRepository.findEditionByTitle(edition.getTitle())){
//            return "catalog";
//        }
            usersRepository.addSubscription(subscription);

        return "redirect:personal/mySubscriptions";
    }

    @GetMapping("/personal/mySubscriptions")
    public String allMySubscriptions(Model model) {
        List<Subscription> subscriptionsList = usersRepository.findAllSubscriptions();
        model.addAttribute("subscriptionsList", subscriptionsList);
        return "userSubscriptions";
    }

}
