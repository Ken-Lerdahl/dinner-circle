package com.dinnercircle.dinnercircle.models;

import org.javatuples.Triplet;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Recipe extends AbstractEntity{

    @ManyToMany
    private List<Ingredient> ingredients = new ArrayList<>();

    @NotNull(message = "Please add at least one ingredient")
    private List<Triplet<Double, UnitsOfMeasurement, Ingredient>> ingredientList = new ArrayList<>();

    @NotBlank(message = "Name of recipe is required")
    @Size(min = 5, max = 50, message = "Name of recipe must be between 5 and 50 characters")
    private String name;

    @NotBlank(message = "Please enter what type of recipe this is")
    @Size(min = 3, max = 20, message = "Recipe type should be between 3 and 20 characters")
    private String recipeType;

    @NotNull(message = "Please enter the recipe steps")
    private String recipeSteps;

    private LocalDate lastMade;

    private Boolean favorite;

    public Recipe() {};

    public Recipe(String name, String recipeType, List<Triplet<Double, UnitsOfMeasurement, Ingredient>> ingredientList, String recipeSteps, LocalDate lastMade, Boolean favorite) {
        this.name = name;
        this.recipeType = recipeType;
        this.ingredientList = ingredientList;
        this.recipeSteps = recipeSteps;
        this.lastMade = lastMade;
        this.favorite = favorite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecipeType() {
        return recipeType;
    }

    public void setRecipeType(String mealType) {
        this.recipeType = mealType;
    }

    public LocalDate getLastMade() {
        return lastMade;
    }

    public void setLastMade(LocalDate lastMade) {
        this.lastMade = lastMade;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Triplet<Double, UnitsOfMeasurement, Ingredient>> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Triplet<Double, UnitsOfMeasurement, Ingredient>> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public void addIngredientList(Triplet<Double, UnitsOfMeasurement, Ingredient> ingredientListAddition) {
        this.ingredientList.add(ingredientListAddition);
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public String getRecipeSteps() {
        return recipeSteps;
    }

    public void setRecipeSteps(String recipeSteps) {
        this.recipeSteps = recipeSteps;
    }
}
