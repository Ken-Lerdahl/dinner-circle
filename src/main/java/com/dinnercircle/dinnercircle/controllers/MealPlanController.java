package com.dinnercircle.dinnercircle.controllers;

import com.dinnercircle.dinnercircle.models.MealPlan;
import com.dinnercircle.dinnercircle.models.Recipe;
import com.dinnercircle.dinnercircle.models.SearchRepository;
import com.dinnercircle.dinnercircle.models.User;
import com.dinnercircle.dinnercircle.models.data.MealPlanRepository;
import com.dinnercircle.dinnercircle.models.data.RecipeRepository;
import com.dinnercircle.dinnercircle.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("mealplan")
public class MealPlanController {

    @Autowired
    private MealPlanRepository mealPlanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;


    @GetMapping
    public String displayMealplan(Model model) {

        User currentUser = SearchRepository.getCurrentUser(userRepository);


        model.addAttribute("title", "Meal Plan");
        if (SearchRepository.getMealPlanForUser(userRepository) != null) {
            MealPlan currentUserMealPlan = SearchRepository.getMealPlanForUser(userRepository);
            model.addAttribute("mealPlan", currentUserMealPlan);
        }
        else {
            MealPlan newUserMealPlan = new MealPlan();
            SearchRepository.getCurrentUser(userRepository).setMealPlan(newUserMealPlan);
            userRepository.save(currentUser);
//            mealPlanRepository.save(newUserMealPlan);
//            newUserMealPlan.setUser(currentUser);
//            mealPlanRepository.findById(newUserMealPlan.getId()).get().setUser(currentUser);
//            userRepository.save(currentUser);
            model.addAttribute("mealPlan", newUserMealPlan);
            return "mealplan/welcome";
//            model.addAttribute(new MealPlan());
//            userRepository.save(currentUser);

        }

        return "mealplan/index";
    }

    @PostMapping
    public String processMealPlan(Model model) {

// add code to either process buttons separately or together
        return "mealplan/index";
    }

    @GetMapping("selectmeal/{mealDay}")
    public String displaySelectMeal(Model model, @PathVariable String mealDay) {
        model.addAttribute("title", "Select a Meal");
        model.addAttribute("recipes", recipeRepository.findAll());
        return "mealplan/selectmeal";
    }

    @PostMapping("selectmeal/{mealDay}")
    public String processSelectMeal(Model model, @PathVariable String mealDay, @RequestParam int recipeId) {
        MealPlan currentUserMealPlan = SearchRepository.getMealPlanForUser(userRepository);
        switch (mealDay) {
            case "monday" -> currentUserMealPlan.setMonday(recipeId);
            case "tuesday" -> currentUserMealPlan.setTuesday(recipeId);
            case "wednesday" -> currentUserMealPlan.setWednesday(recipeId);
            case "thursday" -> currentUserMealPlan.setThursday(recipeId);
            case "friday" -> currentUserMealPlan.setFriday(recipeId);
            case "saturday" -> currentUserMealPlan.setSaturday(recipeId);
            case "sunday" -> currentUserMealPlan.setSunday(recipeId);
        }
            mealPlanRepository.save(currentUserMealPlan);
//        add buttons on view to get recipeId
        return "mealplan/index";
    }

    @GetMapping("welcome")
    public String displayWelcome(Model model) {
        model.addAttribute("title", "Meal Plan Tutorial");
        return "mealplan/welcome";
    }
}


