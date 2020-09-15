package com.dinnercircle.dinnercircle.controllers;

import com.dinnercircle.dinnercircle.models.*;
import com.dinnercircle.dinnercircle.models.data.IngredientListItemRepostiory;
import com.dinnercircle.dinnercircle.models.data.IngredientRepository;
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

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private IngredientListItemRepostiory ingredientListItemRepostiory;

    @GetMapping
    public String displayRecipes(Model model) {
        model.addAttribute("title", "All Recipes");
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipes/index";
    }

    @GetMapping("add")
    public String displayAddRecipeForm(Model model) {
        model.addAttribute("title", "Create a Recipe");
        model.addAttribute(new Recipe());
        return "recipes/add";
    }

    @PostMapping("add")
    public String processAddRecipeForm(@ModelAttribute @Valid Recipe newRecipe,
                                       Errors errors, Model model) {
//    , @RequestParam int ingredient, @RequestParam double quantity, @RequestParam UnitsOfMeasurement unit ,


        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a Recipe");

            return "recipes/add";
        }

        recipeRepository.save(newRecipe);
        int recipeId = newRecipe.getId();
        return "redirect:addingredients/" + recipeId;
    }

    @GetMapping("addingredients/{recipeId}")
    public String displayAddIngredients(Model model, @PathVariable int recipeId) {

        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        if (optRecipe.isPresent()) {
            Recipe recipe = (Recipe) optRecipe.get();
            model.addAttribute("title", "Add Ingredients");
            model.addAttribute("recipe", recipe);
            model.addAttribute("ingredients", ingredientRepository.findAll());
            model.addAttribute("units", UnitsOfMeasurement.values());
            model.addAttribute(new IngredientListItem());
            return "recipes/addingredients";
        } else {
            return "redirect:../";
        }
    }

    @RequestMapping(value="addingredients/{recipeId}", params="next", method=RequestMethod.POST)
    public String processAddIngredients(@ModelAttribute @Valid IngredientListItem newIngredientListItem,
                                        Errors errors, Model model, @PathVariable int recipeId,
                                        @RequestParam int ingredientOnListItem) {

        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
//        if (optRecipe.isPresent()) {
            Recipe recipe = (Recipe) optRecipe.get();
            model.addAttribute("recipe", recipe);
//        }
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Ingredients");

            return "recipes/addingredients/" + recipeId;
        }
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(ingredientOnListItem);
            if(optionalIngredient.isPresent()) {
                Ingredient ingredientToSave = (Ingredient) optionalIngredient.get();
                newIngredientListItem.setIngredient(ingredientToSave);
                newIngredientListItem.setRecipe(recipe);

                ingredientListItemRepostiory.save(newIngredientListItem);
                recipeRepository.save(recipe);
            }
        return "redirect:../addsteps/" + recipeId;
    }

    @RequestMapping(value="addingredients/{recipeId}", params="add", method=RequestMethod.POST)
    public String processMoreAddIngredients(@ModelAttribute @Valid IngredientListItem newIngredientListItem,
                                        Errors errors, Model model, @PathVariable int recipeId,
                                        @RequestParam int ingredientOnListItem) {

        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
//        if (optRecipe.isPresent()) {
        Recipe recipe = (Recipe) optRecipe.get();
        model.addAttribute("recipe", recipe);
//        }
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Ingredients");

            return "recipes/addingredients/" + recipeId;
        }
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(ingredientOnListItem);
        if(optionalIngredient.isPresent()) {
            Ingredient ingredientToSave = (Ingredient) optionalIngredient.get();
            newIngredientListItem.setIngredient(ingredientToSave);
            newIngredientListItem.setRecipe(recipe);

            ingredientListItemRepostiory.save(newIngredientListItem);
            recipeRepository.save(recipe);
        }
        return "redirect:" + recipeId;
    }

    @GetMapping("addsteps/{recipeId}")
    public String displayAddSteps(Model model, @PathVariable int recipeId) {
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        if (optRecipe.isPresent()) {
            Recipe recipe = (Recipe) optRecipe.get();
            model.addAttribute("title", "Add Ingredients");
            model.addAttribute("recipe", recipe);
            model.addAttribute("ingredientListItems",
                    SearchRepository.getRecipeIngredientListFromRepository(ingredientListItemRepostiory, recipeId));
            return "recipes/addsteps";
        } else {
            return "redirect:../";
        }
    }

    @PostMapping("addsteps/{recipeId}")
    public String processAddSteps(Model model, @PathVariable int recipeId, @RequestParam String recipeSteps) {

        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        Recipe recipe = (Recipe) optRecipe.get();
        model.addAttribute("recipe", recipe);
        recipe.setRecipeSteps(recipeSteps);
        recipeRepository.save(recipe);
        return "redirect:../view/" + recipeId;
    }



    @GetMapping("view/{recipeId}")
    public String displayViewRecipe(Model model, @PathVariable int recipeId) {


        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);

        if (optRecipe.isPresent()) {
            Recipe recipe = (Recipe) optRecipe.get();

            model.addAttribute("recipe", recipe);
            model.addAttribute("ingredientListItems",
                    SearchRepository.getRecipeIngredientListFromRepository(ingredientListItemRepostiory, recipeId));

            return "recipes/view";
        } else {
            return "redirect:../";
        }
    }

    @PostMapping

    @GetMapping(value = "edit/{recipeId}")
    public String displayEditRecipe(Model model, @PathVariable int recipeId) {
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);

        if (optRecipe.isPresent()) {
            Recipe recipe = (Recipe) optRecipe.get();

            model.addAttribute("recipe", recipe);
            model.addAttribute("ingredientListItems",
                    SearchRepository.getRecipeIngredientListFromRepository(ingredientListItemRepostiory, recipeId));
            model.addAttribute("ingredients", ingredientRepository.findAll());
            model.addAttribute("units", UnitsOfMeasurement.values());

            return "recipes/edit";
        } else {
            return "redirect:../";
        }
    }

    @PostMapping(value = "edit/{recipeId}", params = "saveChanges")
    public String processEditRecipe(Model model, @PathVariable int recipeId, @RequestParam String name, @RequestParam String recipeType) {
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);

        if (optRecipe.isPresent()) {
            Recipe recipe = (Recipe) optRecipe.get();

            recipe.setName(name);
            recipe.setRecipeType(recipeType);
            recipeRepository.save(recipe);

        }

        return "redirect:../view/" + recipeId;
    }


}
