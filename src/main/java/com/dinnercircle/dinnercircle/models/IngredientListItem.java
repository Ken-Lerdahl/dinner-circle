package com.dinnercircle.dinnercircle.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class IngredientListItem extends AbstractEntity{


    @ManyToOne
    private Recipe recipe;

    private double measuredAmountOnListItem;

    private UnitsOfMeasurement unitOnListItem;

    @ManyToOne
    private Ingredient ingredientOnListItem;

    public IngredientListItem() {};

    public IngredientListItem(double measuredAmountOnListItem, UnitsOfMeasurement unitOnListItem, Ingredient ingredientOnListItem) {
        this.measuredAmountOnListItem = measuredAmountOnListItem;
        this.unitOnListItem = unitOnListItem;
        this.ingredientOnListItem = ingredientOnListItem;
    }

    public double getMeasuredAmountOnListItem() {
        return measuredAmountOnListItem;
    }

    public void setMeasuredAmountOnListItem(double measuredAmountOnListItem) {
        this.measuredAmountOnListItem = measuredAmountOnListItem;
    }

    public UnitsOfMeasurement getUnitOnListItem() {
        return unitOnListItem;
    }

    public void setUnitOnListItem(UnitsOfMeasurement unitOnListItem) {
        this.unitOnListItem = unitOnListItem;
    }


        public Ingredient getIngredientOnListItem() {
        return ingredientOnListItem;
    }

    public void setIngredient(Ingredient ingredientOnListItem) {
        this.ingredientOnListItem = ingredientOnListItem;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
