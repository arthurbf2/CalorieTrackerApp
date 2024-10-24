package com.arthurbf.CalorieTrackerApp.repositories;

import com.arthurbf.CalorieTrackerApp.models.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MealRepository extends JpaRepository<Meal, UUID> {
    Optional<Meal> findByUser_IdAndDateAndMealType(UUID user_id, LocalDate date, Meal.MealType mealType);
    List<Meal> findAllByUserIdAndDate(UUID user_id, LocalDate date);
}
