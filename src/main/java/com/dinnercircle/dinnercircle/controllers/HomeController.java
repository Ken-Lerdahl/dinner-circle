package com.dinnercircle.dinnercircle.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("home")
    public String displayHome() {
        return "/home";
    }

    @RequestMapping("")
    public String displayIndex() {
        return "/welcome";
    }

    @RequestMapping("welcome")
    public String displayWelcome() { return "/welcome";}

}