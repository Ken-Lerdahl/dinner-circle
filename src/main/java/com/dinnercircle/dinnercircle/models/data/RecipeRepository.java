package com.dinnercircle.dinnercircle.models.data;


import com.dinnercircle.dinnercircle.models.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
}
