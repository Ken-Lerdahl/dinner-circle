package com.dinnercircle.dinnercircle.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ShoppingListItem extends AbstractEntity{


    @ManyToOne
    private ShoppingList shoppingList;

    private double measuredAmountOnListItem;

    private UnitsOfMeasurement unitOnListItem;

    @ManyToOne
    private Ingredient ingredientOnListItem;

    public ShoppingListItem() {};

    public ShoppingListItem(double measuredAmountOnListItem, UnitsOfMeasurement unitOnListItem, Ingredient ingredientOnListItem) {
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

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }
}
