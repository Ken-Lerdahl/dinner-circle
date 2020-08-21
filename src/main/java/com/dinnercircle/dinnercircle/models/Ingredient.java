package com.dinnercircle.dinnercircle.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ingredient extends AbstractEntity{


    private String name;

    @ManyToMany(mappedBy = "ingredients")
    private List<Recipe> recipes = new ArrayList<>();

    public Ingredient() {};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
