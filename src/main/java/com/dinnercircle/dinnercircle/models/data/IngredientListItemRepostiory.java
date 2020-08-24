package com.dinnercircle.dinnercircle.models.data;


import com.dinnercircle.dinnercircle.models.IngredientListItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientListItemRepostiory extends CrudRepository<IngredientListItem, Integer> {
}
