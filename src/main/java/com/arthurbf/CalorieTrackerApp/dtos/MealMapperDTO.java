package com.arthurbf.CalorieTrackerApp.dtos;

import com.arthurbf.CalorieTrackerApp.models.Meal;
import com.arthurbf.CalorieTrackerApp.models.MealItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MealMapperDTO implements Function<Meal, MealResponseDTO> {
    @Override
    public MealResponseDTO apply(Meal meal){
        List<ItemResponseDTO> mealItemsDTO  = meal.getMealItems()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return new MealResponseDTO(meal.getDate(), mealItemsDTO, meal.getMealType(), meal.calculateTotalCalories(),
                meal.calculateTotalFat(), meal.calculateTotalCarbs(), meal.calculateTotalProtein());
    }

    private ItemResponseDTO toResponseDTO(MealItem mealItem) {
        return new ItemResponseDTO(
                mealItem.getFood().getName(),
                mealItem.getQuantity(),
                Math.round(mealItem.getCalories()),
                Math.round(mealItem.getProteins()),
                Math.round(mealItem.getFats()),
                Math.round(mealItem.getCarbs())
        );
    }
}
