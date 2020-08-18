package com.dinnercircle.dinnercircle.controllers;

import com.dinnercircle.dinnercircle.models.data.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("meals")
public class MealController {

    @Autowired
    private MealRepository mealRepository;

    @GetMapping("viewall")
    public String displayMealsViewAll(Model model) {
        model.addAttribute("title", "All Meals");
        model.addAttribute("meals", mealRepository.findAll());
        return "meals/viewall";
    }
}
