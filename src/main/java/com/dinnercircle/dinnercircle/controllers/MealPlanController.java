package com.dinnercircle.dinnercircle.controllers;

import com.dinnercircle.dinnercircle.models.MealPlan;
import com.dinnercircle.dinnercircle.models.Recipe;
import com.dinnercircle.dinnercircle.models.SearchRepository;
import com.dinnercircle.dinnercircle.models.User;
import com.dinnercircle.dinnercircle.models.data.MealPlanRepository;
import com.dinnercircle.dinnercircle.models.data.UserRepository;
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

    @Autowired
    private UserRepository userRepository;



    @GetMapping
    public String displayMealplan(Model model) {

        //currently creates a new meal plan for new users, but first time loading page throws a no such element exception

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
            mealPlanRepository.findById(newUserMealPlan.getId()).get().setUser(currentUser);
            userRepository.save(currentUser);
            model.addAttribute("mealPlan", newUserMealPlan);

        }

        return "mealplan/index";
    }
}


