package com.dinnercircle.dinnercircle.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("home")
    public String index() {
        return "/home";
    }

    @GetMapping("mealplan")
    public String displayMealplan() {
        return "mealplan";
    }

}