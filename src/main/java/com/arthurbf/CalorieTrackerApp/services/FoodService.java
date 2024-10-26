package com.arthurbf.CalorieTrackerApp.services;

import com.arthurbf.CalorieTrackerApp.dtos.FoodResponseDTO;
import com.arthurbf.CalorieTrackerApp.models.Food;
import com.arthurbf.CalorieTrackerApp.repositories.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Optional<Food> getFood(UUID food_id) {
        return foodRepository.findById(food_id);
    }

    public List<FoodResponseDTO> getFoods(String name) {
        return foodRepository.findAllByName(name).stream()
                .map(food -> new FoodResponseDTO(food.getName(), food.getId(), food.getCaloriesPerServing()))
                .collect(Collectors.toList());
    }
}
