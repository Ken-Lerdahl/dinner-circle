package com.dinnercircle.dinnercircle.models;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User  extends AbstractEntity{

    @NotNull
    private String username;

    @NotNull
    private String pwHash;

    @NotNull
    private String email;

    @NotNull
    private String phoneNum;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    private MealPlan mealPlan;

    @OneToOne(cascade = CascadeType.ALL)
    private ShoppingList shoppingList;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {}

    public User(String username, String password, String email, String phoneNum, String firstName, String lastName) {
        this.username = username;
        this.pwHash = encoder.encode(password);
        this.email = email;
        this.phoneNum = phoneNum;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

    public MealPlan getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(MealPlan mealPlan) {
        this.mealPlan = mealPlan;
    }

    public ShoppingList getShoppingList() {return shoppingList;}

    public void setShoppingList(ShoppingList shoppingList) {this.shoppingList = shoppingList;}


}
