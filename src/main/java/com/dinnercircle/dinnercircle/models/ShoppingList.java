package com.dinnercircle.dinnercircle.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingList extends AbstractEntity {

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "shoppingList")
    private List<ShoppingListItem> shoppingListItems = new ArrayList<>();

    public ShoppingList() {};

    public ShoppingList(List<ShoppingListItem> shoppingListItems) {
        this.shoppingListItems = shoppingListItems;
    }

    public List<ShoppingListItem> getShoppingListItems() {return shoppingListItems;}


}
