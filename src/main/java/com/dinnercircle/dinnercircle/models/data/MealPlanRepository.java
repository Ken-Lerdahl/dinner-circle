package com.dinnercircle.dinnercircle.models.data;

import com.dinnercircle.dinnercircle.models.MealPlan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealPlanRepository extends CrudRepository<MealPlan, Integer> {
}
