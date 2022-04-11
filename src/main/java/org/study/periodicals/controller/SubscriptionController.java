package org.study.periodicals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.study.periodicals.model.Edition;
import org.study.periodicals.model.Subscription;
import org.study.periodicals.service.EditionService;
import org.study.periodicals.service.SubscriptionService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
public class SubscriptionController {

    private SubscriptionService subscriptionService;

    private EditionService editionService;

    public SubscriptionController(SubscriptionService subscriptionService, EditionService editionService) {
        this.subscriptionService = subscriptionService;
        this.editionService = editionService;
    }

    @GetMapping("/personal/mySubscriptions")
    public String allMySubscriptions(Model model) {
        List<Subscription> subscriptionsList = subscriptionService.getAllSubscriptions();
        model.addAttribute("subscriptionsList", subscriptionsList);
        return "userSubscriptions";
    }

    @GetMapping("/personal/addFormSubscription")// показывает пустую таблицу
    public String saveUsersEdition() {
        return "shoppingCart";
    }

    @PostMapping("/personal/addFormSubscription")// кладу объект edition
    public ModelAndView addEditionInSubscription(@RequestParam(value = "editionId") Integer editionId, HttpSession session) {
        Edition edition = editionService.findEditionById(editionId);
        String title = edition.getTitle();
        Integer actualPrice = (edition.getRecommendedPrice() * 120) / 100;
        //      session.getAttribute(title);
//        Object priceAttribute = session.getAttribute(String.valueOf(actualPrice));
        List<Object> titleAndPriceOfEdition = Arrays.asList(title, actualPrice);
 //       session.setAttribute("cart", titleAndPriceOfEdition);
        ModelAndView view = new ModelAndView("shoppingCart");
        view.addObject("cart",titleAndPriceOfEdition);
        return view;
    }


    @PostMapping("/personal/showSubscription") //отрисовка объекта Edition
    public String formSubscription(@ModelAttribute Subscription subscription) {
        Subscription subscriptionOfUser = subscriptionService.getSubscriptionById(subscription.getId());
        if (subscriptionOfUser != null) {
            subscription.setSubscriptionDate(new Date());
            subscriptionService.addSubscriptionToUser(subscription);
            return "redirect:personal/confirmOrder";
        }
        return "shoppingCart";
    }


//    @GetMapping("/personal/shoppingCart")
//    public String userSubscriptionTable(@RequestParam Integer quantity, @RequestParam Date startDate, @RequestParam Date finishDate,
//                                        ModelAndView modelAndView) {

//        if (subscription != null) {
//            view.addObject("subscription", subscription);
//        } else {
//            view.addObject("subscription", new Subscription());
//        }
//
//        return "shoppingCart";
//    }

    @PostMapping("/personal/confirmOrder") //окончательное формирование подписки и отправка формы на оплату
    public String addEditInSubscription(@RequestParam Integer quantity) throws Exception {

        return "redirect:newSubscription";
    }

    @ModelAttribute("subscription")
    public Subscription subscription() {
        return new Subscription();
    }
}









