package com.dinnercircle.dinnercircle.controllers;

import com.dinnercircle.dinnercircle.models.Recipe;
import com.dinnercircle.dinnercircle.models.data.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("recipes")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping
    public String displayRecipes(Model model) {
        model.addAttribute("title", "All Recipes");
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipes/index";
    }

    @GetMapping("add")
    public String displayAddRecipeForm(Model model) {
        model.addAttribute(new Recipe());
        return "recipes/add";
    }

    @PostMapping("add")
    public String processAddRecipeForm(@ModelAttribute @Valid Recipe newRecipe,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a Recipe");

            return "recipes/add";
        }

        recipeRepository.save(newRecipe);
        return "recipes/index";

        // FLESH OUT ADD TEMPLATE AND CONTROLLER METHODS
        // NEED TO PASS INGREDIENTS LIST & UNITS INTO VIEW, THEN
        // ADD INDREDIENTS TO DATABASE
        // ALSO, GET STRING FOR STEPS TO MAKE
    }

    @GetMapping("view/{recipeId}")
    public String displayViewRecipe(Model model, @PathVariable int recipeId) {

        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        if (optRecipe.isPresent()) {
            Recipe recipe = (Recipe) optRecipe.get();
            model.addAttribute("recipe", recipe);
            return "recipes/view";
        } else {
            return "redirect:../";
        }
    }

}
