package com.dinnercircle.dinnercircle.models;

import com.dinnercircle.dinnercircle.controllers.AuthenticationController;
import com.dinnercircle.dinnercircle.models.data.*;



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


//
//    public static MealPlan getMealPlanForUser(MealPlanRepository mealPlanRepository) {
//
//
//        MealPlan userMealPlan = null;
//        for (MealPlan plan : mealPlanRepository.findAll()) {
//            if (plan.getUser().getId() == userId) {
//                userMealPlan = plan;
//            }
//        }
//        return userMealPlan;
//    }

}
