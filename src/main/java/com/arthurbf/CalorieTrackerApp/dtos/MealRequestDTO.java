package com.arthurbf.CalorieTrackerApp.dtos;

import com.arthurbf.CalorieTrackerApp.models.Meal;

import java.time.LocalDate;
import java.util.UUID;

public record MealRequestDTO(UUID user_id, LocalDate localDate, Meal.MealType mealType) {
}
