package com.dinnercircle.dinnercircle.models.data;

import com.dinnercircle.dinnercircle.models.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
}
