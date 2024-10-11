package com.arthurbf.CalorieTrackerApp.services;

import com.arthurbf.CalorieTrackerApp.models.Food;
import com.arthurbf.CalorieTrackerApp.repositories.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Optional<Food> getFood(UUID food_id) {
        return foodRepository.findById(food_id);
    }
}
