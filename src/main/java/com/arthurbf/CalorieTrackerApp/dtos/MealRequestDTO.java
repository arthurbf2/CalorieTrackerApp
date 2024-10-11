package com.arthurbf.CalorieTrackerApp.dtos;

import com.arthurbf.CalorieTrackerApp.models.Meal;

import java.time.LocalDate;

public record MealRequestDTO(LocalDate localDate, Meal.MealType mealType) {
}
