package com.dinnercircle.dinnercircle.controllers;

import com.dinnercircle.dinnercircle.models.Recipe;
import com.dinnercircle.dinnercircle.models.SearchRepository;
import com.dinnercircle.dinnercircle.models.data.MealPlanRepository;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("mealplan")
public class MealPlanController {

    @Autowired
    private MealPlanRepository mealPlanRepository;

    @GetMapping
    public String displayMealplan(Model model) {

        model.addAttribute("title", "Meal Plan");
//        model.addAttribute("mealPlan", SearchRepository.getMealPlanForUser(mealPlanRepository, userId));
        return "mealplan/index";
    }
}
