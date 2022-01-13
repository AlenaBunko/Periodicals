package org.study.periodicals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.study.periodicals.model.User;

import javax.servlet.http.HttpServlet;

@Controller
public class IndexController  {
    
   @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("message","I did it");
        return "hello";
    }

        @GetMapping(value = "/index")
        public ModelAndView main(@ModelAttribute("userNew") User user) {
            ModelAndView modelAndView = new ModelAndView();
 //           modelAndView.addObject("userNew", new User());
            modelAndView.setViewName("index");
            return modelAndView;
        }



    }
