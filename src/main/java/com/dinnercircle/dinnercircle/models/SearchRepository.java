package com.dinnercircle.dinnercircle.models;

import com.dinnercircle.dinnercircle.models.data.IngredientListItemRepostiory;
import com.dinnercircle.dinnercircle.models.data.IngredientRepository;
import com.dinnercircle.dinnercircle.models.data.RecipeRepository;
import com.dinnercircle.dinnercircle.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

}
