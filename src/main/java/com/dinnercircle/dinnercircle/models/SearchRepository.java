package com.dinnercircle.dinnercircle.models;

import com.dinnercircle.dinnercircle.controllers.AuthenticationController;
import com.dinnercircle.dinnercircle.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;



public class SearchRepository {


    public static List<IngredientListItem> getRecipeIngredientListFromRepository(IngredientListItemRepostiory ingListRepo, int recipeId) {

        List<IngredientListItem> results = new ArrayList<>();

        for (IngredientListItem item : ingListRepo.findAll()) {
            if (item.getRecipe().getId() == recipeId) {
                results.add(item);
            }
        }
        return results;
    }


    public static User getCurrentUser(UserRepository userRepository) {
        User currentUser;
        return currentUser = userRepository.findById(AuthenticationController.getCurrentUserId()).get();
    }



    public static MealPlan getMealPlanForUser(UserRepository userRepository) {

        MealPlan userMealPlan;
        return userMealPlan = userRepository.findById(AuthenticationController.getCurrentUserId()).get().getMealPlan();

    }

}
