package com.dinnercircle.dinnercircle.controllers;

import com.dinnercircle.dinnercircle.models.Ingredient;
import com.dinnercircle.dinnercircle.models.data.IngredientRepository;
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
@RequestMapping("ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping
    public String displayIngredients(Model model) {
        model.addAttribute("title", "All Ingredients");
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "ingredients/index";
    }

    @GetMapping("add")
    public String displayAddIngredientForm(Model model) {
        model.addAttribute(new Ingredient());
        return "ingredients/add";
    }

    @PostMapping("add")
    public String processAddIngredientForm(@ModelAttribute @Valid Ingredient newIngredient,
                                      Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add an Ingredient");

            return "ingredients/add";
        }

        ingredientRepository.save(newIngredient);
        return "redirect:";
    }
}
