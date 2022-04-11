package org.study.periodicals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.study.periodicals.model.Edition;
import org.study.periodicals.repository.impl.DefaultEditionsRepository;
import org.study.periodicals.service.EditionService;
import org.study.periodicals.service.SubscriptionService;

import javax.servlet.ServletException;
import java.util.List;

@Controller
public class EditionController {

    EditionService editionService;

    public EditionController(EditionService editionService) {
        this.editionService = editionService;
    }

    @PostMapping("/adminPage/addNewEdition")
    public String addEdition(@RequestParam String title, @RequestParam Integer index,
                             @RequestParam String publishingHouse, @RequestParam Integer recommendedPrice) throws ServletException {
       editionService.addEdition(title, index, publishingHouse, recommendedPrice);
        return "redirect:/catalog";
    }


    @GetMapping("/catalog")
    public ModelAndView catalogEditions(ModelAndView modelAndView){
        List<Edition> editionList = editionService.getAllEditions();
        modelAndView.setViewName("catalog");
        modelAndView.addObject("list", editionList);
        return modelAndView;
    }

    @PostMapping("/adminPage/delete")
    public String deleteEdition(@RequestParam(value = "id") Integer id){
        editionService.deleteEdition(id);
        return "redirect:/catalog";
    }
}
