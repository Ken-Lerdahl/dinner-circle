package com.dinnercircle.dinnercircle.controllers;

import com.dinnercircle.dinnercircle.models.Recipe;
import com.dinnercircle.dinnercircle.models.data.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("recipes")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping
    public String displayRecipes(Model model) {
        model.addAttribute("title", "All Recipes");
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipes/viewall";
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
    }

}
