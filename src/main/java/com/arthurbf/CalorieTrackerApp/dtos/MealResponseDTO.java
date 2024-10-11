package com.arthurbf.CalorieTrackerApp.dtos;

import com.arthurbf.CalorieTrackerApp.models.Meal;
import com.arthurbf.CalorieTrackerApp.models.MealItem;

import java.time.LocalDate;
import java.util.List;

public record MealResponseDTO(LocalDate date, List<MealItem> mealItems, Meal.MealType mealType) {
}
