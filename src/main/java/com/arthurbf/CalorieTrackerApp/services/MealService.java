package com.arthurbf.CalorieTrackerApp.services;

import com.arthurbf.CalorieTrackerApp.dtos.ItemRequestDTO;
import com.arthurbf.CalorieTrackerApp.dtos.MealRequestDTO;
import com.arthurbf.CalorieTrackerApp.dtos.MealResponseDTO;
import com.arthurbf.CalorieTrackerApp.models.Meal;
import com.arthurbf.CalorieTrackerApp.models.MealItem;
import com.arthurbf.CalorieTrackerApp.repositories.MealRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class MealService {

    private final MealRepository mealRepository;
    private final UserService userService;
    private final FoodService foodService;

    public MealService(MealRepository mealRepository, UserService userService, FoodService foodService) {
        this.mealRepository = mealRepository;
        this.userService = userService;
        this.foodService = foodService;
    }

    @Transactional
    public void createMeal(UUID user_id, MealRequestDTO mealRequestDTO) {
        var mealDb = mealRepository.findByUser_IdAndDateAndMealType(
                user_id,
                mealRequestDTO.localDate(),
                mealRequestDTO.mealType()
        );
        if(mealDb.isPresent()) {
            throw new IllegalArgumentException("You can only have one " + mealRequestDTO.mealType() + " per day.");
        }
        var meal = new Meal();
        var user = userService.getUser(user_id)
                .orElseThrow(() -> new RuntimeException("User not found."));
        meal.setUser(user);
        meal.setMealType(mealRequestDTO.mealType());
        meal.setDate(mealRequestDTO.localDate());
        meal.setMealItems(new ArrayList<>());
        mealRepository.save(meal);
    }

    @Transactional
    public void addMealItems(UUID user_id, UUID meal_id, ItemRequestDTO itemRequestDTO) {
        var meal = mealRepository.findById(meal_id)
                .orElseThrow(() -> new RuntimeException("Meal not found"));
        var user = userService.getUser(user_id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        var food = foodService.getFood(itemRequestDTO.food_id())
                .orElseThrow(() -> new RuntimeException("Food not found"));

        if (!user.equals(meal.getUser())) {
                throw new RuntimeException("The meal does not belong to this user");
        }
        var mealItem = new MealItem(meal, food, itemRequestDTO.quantity());
        meal.getMealItems().add(mealItem);
        mealRepository.save(meal);
    }

    public MealResponseDTO getMealDetails(UUID user_id, UUID meal_id) {
        var meal = mealRepository.findById(meal_id)
                .orElseThrow(() -> new RuntimeException("Meal not found"));
        var user = userService.getUser(user_id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(!user.equals(meal.getUser())) {
            throw new RuntimeException("Meal does not belong to this user");
        }
        return new MealResponseDTO(meal.getDate(), meal.getMealItems(), meal.getMealType());
    }
}
