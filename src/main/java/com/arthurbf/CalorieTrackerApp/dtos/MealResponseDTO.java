package com.arthurbf.CalorieTrackerApp.dtos;

import com.arthurbf.CalorieTrackerApp.models.Meal;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record MealResponseDTO(
        UUID id,
        LocalDate date,
        List<ItemResponseDTO> mealItems,
        Meal.MealType mealType,
        double totalCalories,
        double totalFat,
        double totalCarbs,
        double totalProtein) {
}
