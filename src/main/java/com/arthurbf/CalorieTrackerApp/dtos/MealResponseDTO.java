package com.arthurbf.CalorieTrackerApp.dtos;

import com.arthurbf.CalorieTrackerApp.models.Meal;

import java.time.LocalDate;
import java.util.List;

public record MealResponseDTO(LocalDate date, List<ItemResponseDTO> mealItems, Meal.MealType mealType) {
}
