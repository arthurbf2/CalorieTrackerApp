package com.arthurbf.CalorieTrackerApp.controllers;


import com.arthurbf.CalorieTrackerApp.dtos.FoodResponseDTO;
import com.arthurbf.CalorieTrackerApp.services.FoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/foods")
    public ResponseEntity<List<FoodResponseDTO>> getFoodList(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK).body(foodService.getFoods(name));
    }


}
