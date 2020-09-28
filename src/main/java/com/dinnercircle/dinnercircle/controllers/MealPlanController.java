package com.dinnercircle.dinnercircle.controllers;

import com.dinnercircle.dinnercircle.models.*;
import com.dinnercircle.dinnercircle.models.data.IngredientListItemRepostiory;
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

    @Autowired
    private IngredientListItemRepostiory ingredientListItemRepostiory;


    @GetMapping
    public String displayMealplan(Model model) {

        User currentUser = SearchRepository.getCurrentUser(userRepository);


        model.addAttribute("title", "Meal Plan");
        if (SearchRepository.getMealPlanForUser(userRepository) != null) {

            MealPlan currentUserMealPlan = SearchRepository.getMealPlanForUser(userRepository);
            model.addAttribute("mealPlan", currentUserMealPlan);

            Optional<Recipe> optMondayRecipe = recipeRepository.findById(currentUserMealPlan.getMonday());
            Optional<Recipe> optTuesdayRecipe = recipeRepository.findById(currentUserMealPlan.getTuesday());
            Optional<Recipe> optWednesdayRecipe = recipeRepository.findById(currentUserMealPlan.getWednesday());
            Optional<Recipe> optThursdayRecipe = recipeRepository.findById(currentUserMealPlan.getThursday());
            Optional<Recipe> optFridayRecipe = recipeRepository.findById(currentUserMealPlan.getFriday());
            Optional<Recipe> optSaturdayRecipe = recipeRepository.findById(currentUserMealPlan.getSaturday());
            Optional<Recipe> optSundayRecipe = recipeRepository.findById(currentUserMealPlan.getSunday());

            if (optMondayRecipe.isPresent()) {
                model.addAttribute("mondayMeal", optMondayRecipe.get());

            } else {
                currentUserMealPlan.setMonday(0);
            }

            if (optTuesdayRecipe.isPresent()) {
                model.addAttribute("tuesdayMeal", optTuesdayRecipe.get());
            } else {
                currentUserMealPlan.setTuesday(0);
            }

            if (optWednesdayRecipe.isPresent()) {
                model.addAttribute("wednesdayMeal", optWednesdayRecipe.get());
            } else {
                currentUserMealPlan.setWednesday(0);
            }

            if (optThursdayRecipe.isPresent()) {
                model.addAttribute("thursdayMeal", optThursdayRecipe.get());
            } else {
                currentUserMealPlan.setThursday(0);
            }

            if (optFridayRecipe.isPresent()) {
                model.addAttribute("fridayMeal", optFridayRecipe.get());
            } else {
                currentUserMealPlan.setFriday(0);
            }

            if (optSaturdayRecipe.isPresent()) {
                model.addAttribute("saturdayMeal", optSaturdayRecipe.get());
            } else {
                currentUserMealPlan.setSaturday(0);
            }

            if (optSundayRecipe.isPresent()) {
                model.addAttribute("sundayMeal", optSundayRecipe.get());
            } else {
                currentUserMealPlan.setSunday(0);
            }
            mealPlanRepository.save(currentUserMealPlan);


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

    @GetMapping("readytocook/{recipeId}")
    public String displayReadyToCook(Model model, @PathVariable int recipeId) {
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);


            Recipe recipe = (Recipe) optRecipe.get();

            model.addAttribute("recipe", recipe);
            model.addAttribute("ingredientListItems",
                    SearchRepository.getRecipeIngredientListFromRepository(ingredientListItemRepostiory, recipeId));
            recipe.setLastMade(LocalDate.now());
            recipeRepository.save(recipe);
            if (recipe.getFavorite() != null && recipe.getFavorite()) {
                model.addAttribute("favStatus", "Remove As Favorite");
            }
            else {
                recipe.setFavorite(false);
                recipeRepository.save(recipe);
                model.addAttribute("favStatus", "Set As Favorite");
            }
        return "mealplan/readytocook";
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


