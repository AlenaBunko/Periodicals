package org.study.periodicals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.study.periodicals.model.Edition;
import org.study.periodicals.model.Subscription;
import org.study.periodicals.model.User;
import org.study.periodicals.service.SubscriptionService;

import java.util.Date;
import java.util.List;

@Controller
public class SubscriptionController {

    private SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/personal/mySubscriptions")
    public String allMySubscriptions(Model model) {
        List<Subscription> subscriptionsList = subscriptionService.getAllSubscriptions();
        model.addAttribute("subscriptionsList", subscriptionsList);
        return "userSubscriptions";
    }

    @GetMapping("/personal/addFormSubscription")
    public String formSubscription() {
        return "addSubscriptions";
    }

    @PostMapping("/personal/addFormSubscription")
    public String addSubscription(@RequestParam String title, @RequestParam Integer quantity,
                                  @RequestParam Date startDate, @RequestParam Date finishDate,
                                  @RequestParam Date subscriptionDate, @SessionAttribute User user) throws Exception {
        Subscription subscription = null;
        subscription = subscriptionService.createSubscription(title, quantity, finishDate, startDate, subscriptionDate, user);
        user.getSubscriptions().add(subscription);
        return "redirect:personal/payment";
    }


}









