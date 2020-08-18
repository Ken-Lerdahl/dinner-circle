package com.dinnercircle.dinnercircle.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Meal extends AbstractEntity{

    @NotBlank(message = "Name of meal is required")
    @Size(min = 5, max = 50, message = "Name of meal must be between 5 and 50 characters")
    private String name;

    @NotBlank(message = "Please enter what type of meal this is")
    @Size(min = 3, max = 20, message = "Meal type should be between 3 and 20 characters")
    private String mealType;

    private LocalDate lastMade;

    private Boolean favorite;

    public Meal() {};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
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

    public Meal(String name, String mealType, LocalDate lastMade, Boolean favorite) {
        this.name = name;
        this.mealType = mealType;
        this.lastMade = lastMade;
        this.favorite = favorite;


    }
}
