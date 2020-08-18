package com.dinnercircle.dinnercircle.models.data;


import com.dinnercircle.dinnercircle.models.Meal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends CrudRepository<Meal, Integer> {
}
