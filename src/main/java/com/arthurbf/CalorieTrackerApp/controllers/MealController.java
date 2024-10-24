package com.arthurbf.CalorieTrackerApp.controllers;

import com.arthurbf.CalorieTrackerApp.dtos.ItemRequestDTO;
import com.arthurbf.CalorieTrackerApp.dtos.MealRequestDTO;
import com.arthurbf.CalorieTrackerApp.dtos.MealResponseDTO;
import com.arthurbf.CalorieTrackerApp.models.User;
import com.arthurbf.CalorieTrackerApp.services.MealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping("/meals")
    public ResponseEntity<String> createMeal(@RequestBody MealRequestDTO mealRequestDTO, Authentication auth) {
        var requestUser = (User) auth.getPrincipal();
        mealService.createMeal(requestUser.getId(), mealRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }

    @PostMapping("/meals/{meal_id}/items")
    public ResponseEntity<String> addMealItem(@PathVariable UUID meal_id,
                                              @RequestBody ItemRequestDTO ItemRequestDTO, Authentication auth) {

        var requestUser = (User) auth.getPrincipal();
        mealService.addMealItems(requestUser.getId(), meal_id, ItemRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }

    @GetMapping("/meals/{meal_id}")
    public ResponseEntity<MealResponseDTO> getMealDetails(@PathVariable UUID meal_id, Authentication auth) {
        var requestUser = (User) auth.getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(mealService.getMealDetails(requestUser.getId(), meal_id));
    }

    @GetMapping("/meals")
    public ResponseEntity<List<MealResponseDTO>> getMealsFromDate(@RequestParam LocalDate date, Authentication auth) {
        var requestUser = (User) auth.getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(mealService.getMealsFromDate(requestUser.getId(), date));
    }

    @PatchMapping("/meals/{meal_id}/items/{meal_item_id}")
    public ResponseEntity<String> updateMealItem(@PathVariable UUID meal_id,
                                                 @PathVariable UUID meal_item_id,
                                                 @RequestBody ItemRequestDTO itemRequestDTO, Authentication auth) {

        var requestUser = (User) auth.getPrincipal();
        mealService.updateMeal(requestUser.getId(), meal_id, meal_item_id, itemRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Meal updated");
    }

    @DeleteMapping("/meals/{meal_id}")
    public ResponseEntity<String> deleteMeal(@PathVariable UUID meal_id, Authentication auth) {
        var requestUser = (User) auth.getPrincipal();
        mealService.deleteMeal(requestUser.getId(), meal_id);
        return ResponseEntity.status(HttpStatus.OK).body("Meal deleted");
    }

    @DeleteMapping("/meals/{meal_id}/items/{meal_item_id}")
    public ResponseEntity<String> deleteMealItem(@PathVariable UUID meal_id,
                                                 @PathVariable UUID meal_item_id, Authentication auth) {

        var requestUser = (User) auth.getPrincipal();
        mealService.deleteMealItem(requestUser.getId(), meal_id, meal_item_id);
        return ResponseEntity.status(HttpStatus.OK).body("Meal item deleted");
    }
}
