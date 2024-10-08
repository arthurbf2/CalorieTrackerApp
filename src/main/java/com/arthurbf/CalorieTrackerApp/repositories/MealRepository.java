package com.arthurbf.CalorieTrackerApp.repositories;

import com.arthurbf.CalorieTrackerApp.models.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MealRepository extends JpaRepository<Meal, UUID> {
}
