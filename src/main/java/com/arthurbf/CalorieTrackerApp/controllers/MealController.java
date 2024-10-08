package com.arthurbf.CalorieTrackerApp.controllers;

import com.arthurbf.CalorieTrackerApp.services.MealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping("/users/{user_id}/meals/")
    public ResponseEntity<String> createMeal(@PathVariable UUID user_id) {
        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }

    @PostMapping("/users/{user_id}/meals/{meal_id}/items")
    public ResponseEntity<String> addMealItem(@PathVariable UUID user_id, @PathVariable UUID meal_id) {
        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }

}
