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

import java.time.LocalDate;
import java.util.Optional;

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
            if (currentUserMealPlan.getMonday() != 0) {
                model.addAttribute("mondayMeal", recipeRepository.findById(currentUserMealPlan.getMonday()).get());
            }
            if (currentUserMealPlan.getTuesday() != 0) {
                model.addAttribute("tuesdayMeal", recipeRepository.findById(currentUserMealPlan.getTuesday()).get());
            }
            if (currentUserMealPlan.getWednesday() != 0) {
                model.addAttribute("wednesdayMeal", recipeRepository.findById(currentUserMealPlan.getWednesday()).get());
            }
            if (currentUserMealPlan.getThursday() != 0) {
                model.addAttribute("thursdayMeal", recipeRepository.findById(currentUserMealPlan.getThursday()).get());
            }
            if (currentUserMealPlan.getFriday() != 0) {
                model.addAttribute("fridayMeal", recipeRepository.findById(currentUserMealPlan.getFriday()).get());
            }
            if (currentUserMealPlan.getSaturday() != 0) {
                model.addAttribute("saturdayMeal", recipeRepository.findById(currentUserMealPlan.getSaturday()).get());
            }
            if (currentUserMealPlan.getSunday() != 0) {
                model.addAttribute("sundayMeal", recipeRepository.findById(currentUserMealPlan.getSunday()).get());
            }
        }
        else {
            MealPlan newUserMealPlan = new MealPlan();
            SearchRepository.getCurrentUser(userRepository).setMealPlan(newUserMealPlan);

//            mealPlanRepository.save(newUserMealPlan);
            newUserMealPlan.setUser(currentUser);
            userRepository.save(currentUser);
//            mealPlanRepository.findById(newUserMealPlan.getId()).get().setUser(currentUser);
//            userRepository.save(currentUser);
            model.addAttribute("mealPlan", newUserMealPlan);
            return "redirect:/mealplan";
//            model.addAttribute(new MealPlan());
//            userRepository.save(currentUser);

        }

        return "mealplan/index";
    }

    @PostMapping(value = "", params = "make")
    public String processMealPlanMake(Model model, @RequestParam int mealId) {

        Optional<Recipe> optRecipe = recipeRepository.findById(mealId);

        Recipe recipe = (Recipe) optRecipe.get();
        recipe.setLastMade(LocalDate.now());
        recipeRepository.save(recipe);

        return "redirect:../recipes/view/" + recipe.getId();
    }

    @GetMapping("selectmeal/{mealDay}")
    public String displaySelectMeal(Model model, @PathVariable String mealDay) {
        model.addAttribute("title", "Select a Meal");
        model.addAttribute("recipes", recipeRepository.findAll());
        return "mealplan/selectmeal";
    }

    @RequestMapping(value = "selectmeal/{mealDay}", params = "select", method=RequestMethod.POST)
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
        return "redirect:../";
    }


}


