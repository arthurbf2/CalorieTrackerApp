package com.arthurbf.CalorieTrackerApp.services;

import com.arthurbf.CalorieTrackerApp.dtos.MealRequestDTO;
import com.arthurbf.CalorieTrackerApp.models.Meal;
import com.arthurbf.CalorieTrackerApp.repositories.MealRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MealService {

    private final MealRepository mealRepository;
    private final UserService userService;

    public MealService(MealRepository mealRepository, UserService userService) {
        this.mealRepository = mealRepository;
        this.userService = userService;
    }

    @Transactional
    public void createMeal(MealRequestDTO mealRequestDTO) {
        var mealDb = mealRepository.findByUserAndDateAndMealType(
                mealRequestDTO.user_id(),
                mealRequestDTO.localDate(),
                mealRequestDTO.mealType()
        );
        if(mealDb.isPresent()) {
            throw new IllegalArgumentException("You can only have one " + mealRequestDTO.mealType() + " per day.");
        }
        var meal = new Meal();
        var user = userService.getUser(mealRequestDTO.user_id())
                .orElseThrow(() -> new RuntimeException("User not found."));
        meal.setUser(user);
        meal.setMealType(mealRequestDTO.mealType());
        meal.setDate(mealRequestDTO.localDate());
        meal.setMealItems(new ArrayList<>());
        mealRepository.save(meal);
    }

    @Transactional
    public void addMealItems() {

    }
}
