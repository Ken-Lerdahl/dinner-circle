package com.dinnercircle.dinnercircle.controllers;


import com.dinnercircle.dinnercircle.models.SearchRepository;
import com.dinnercircle.dinnercircle.models.User;
import com.dinnercircle.dinnercircle.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("home")
    public String displayHome(Model model) {

        User currentUser = SearchRepository.getCurrentUser(userRepository);
        model.addAttribute("welcome", "Welcome back, " + currentUser.getUsername() + "!");
        return "/home";
    }

    @RequestMapping("")
    public String displayIndex() {
        return "/welcome";
    }

    @RequestMapping("welcome")
    public String displayWelcome() { return "/welcome";}

}