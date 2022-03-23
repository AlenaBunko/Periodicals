package org.study.periodicals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.study.periodicals.model.Edition;
import org.study.periodicals.repository.impl.DefaultEditionsRepository;

import javax.servlet.ServletException;
import java.util.List;

@Controller
public class EditionController {

    DefaultEditionsRepository editionsRepository;

    public EditionController(DefaultEditionsRepository editionsRepository) {
        this.editionsRepository = editionsRepository;
    }


    @PostMapping("/adminPage/addNewEdition")
    public String addEdition(@ModelAttribute("edition") Edition edition) throws ServletException {
        editionsRepository.addEdition(edition);
        return "catalog";
    }



    @GetMapping("/catalog")
    public ModelAndView catalogEditions(ModelAndView modelAndView){
        List<Edition> editionList = editionsRepository.findAllEditions();
        modelAndView.setViewName("catalog");
        modelAndView.addObject(editionList);
        return modelAndView;
    }
}
