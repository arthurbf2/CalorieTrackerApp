package com.arthurbf.CalorieTrackerApp.services;

import com.arthurbf.CalorieTrackerApp.models.Meal;
import com.arthurbf.CalorieTrackerApp.repositories.MealRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MealService {
    private final MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

}
