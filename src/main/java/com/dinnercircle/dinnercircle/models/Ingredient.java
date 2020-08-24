package com.dinnercircle.dinnercircle.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ingredient extends AbstractEntity{


    private String name;

    @OneToMany(mappedBy = "ingredientOnListItem")
    private List<IngredientListItem> ingredientListItems = new ArrayList<>();

    public Ingredient() {};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IngredientListItem> getIngredientListItems() {
        return ingredientListItems;
    }

    public void setIngredientListItems(List<IngredientListItem> ingredientListItems) {
        this.ingredientListItems = ingredientListItems;
    }

    public void addIngredientListItems(IngredientListItem ingredientListItem) {
        ingredientListItems.add(ingredientListItem);
    }
}
