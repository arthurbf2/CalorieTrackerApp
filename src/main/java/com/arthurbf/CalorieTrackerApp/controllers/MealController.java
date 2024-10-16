package com.arthurbf.CalorieTrackerApp.controllers;

import com.arthurbf.CalorieTrackerApp.dtos.ItemRequestDTO;
import com.arthurbf.CalorieTrackerApp.dtos.MealRequestDTO;
import com.arthurbf.CalorieTrackerApp.dtos.MealResponseDTO;
import com.arthurbf.CalorieTrackerApp.services.MealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping("/users/{user_id}/meals")
    public ResponseEntity<String> createMeal(@PathVariable UUID user_id, @RequestBody MealRequestDTO mealRequestDTO) {
        mealService.createMeal(user_id, mealRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }

    @PostMapping("/users/{user_id}/meals/{meal_id}/items")
    public ResponseEntity<String> addMealItem(@PathVariable UUID user_id, @PathVariable UUID meal_id, @RequestBody ItemRequestDTO ItemRequestDTO) {
        mealService.addMealItems(user_id, meal_id, ItemRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }

    @GetMapping("/users/{user_id}/meals/{meal_id}")
    public ResponseEntity<MealResponseDTO> getMealDetails(@PathVariable UUID user_id, @PathVariable UUID meal_id) {
        return ResponseEntity.status(HttpStatus.OK).body(mealService.getMealDetails(user_id, meal_id));
    }

    @PatchMapping("/users/{user_id}/meals/{meal_id}/items/{meal_item_id}")
    public ResponseEntity<String> updateMealItem(@PathVariable UUID user_id, @PathVariable UUID meal_id,
                                                 @PathVariable UUID meal_item_id,
                                                 @RequestBody ItemRequestDTO itemRequestDTO) {
        mealService.updateMeal(user_id, meal_id, meal_item_id, itemRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Meal updated");
    }

    @DeleteMapping("/users/{user_id}/meals/{meal_id}")
    public ResponseEntity<String> deleteMeal(@PathVariable UUID user_id, @PathVariable UUID meal_id) {
        mealService.deleteMeal(user_id, meal_id);
        return ResponseEntity.status(HttpStatus.OK).body("Meal deleted");
    }

    @DeleteMapping("/users/{user_id}/meals/{meal_id}/items/{meal_item_id}")
    public ResponseEntity<String> deleteMealItem(@PathVariable UUID user_id, @PathVariable UUID meal_id,
                                                 @PathVariable UUID meal_item_id) {
        mealService.deleteMealItem(user_id, meal_id, meal_item_id);
        return ResponseEntity.status(HttpStatus.OK).body("Meal item deleted");
    }
}
