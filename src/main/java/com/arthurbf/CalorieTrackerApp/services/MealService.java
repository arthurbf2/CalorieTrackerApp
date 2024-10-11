package com.arthurbf.CalorieTrackerApp.services;

import com.arthurbf.CalorieTrackerApp.dtos.ItemRequestDTO;
import com.arthurbf.CalorieTrackerApp.dtos.MealMapperDTO;
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
    private final MealMapperDTO mealMapperDTO;

    public MealService(MealRepository mealRepository, UserService userService, FoodService foodService, MealMapperDTO mealMapperDTO) {
        this.mealRepository = mealRepository;
        this.userService = userService;
        this.foodService = foodService;
        this.mealMapperDTO = mealMapperDTO;
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
        var meal = getMealForUser(user_id, meal_id);
        var food = foodService.getFood(itemRequestDTO.food_id())
                .orElseThrow(() -> new RuntimeException("Food not found"));
        var mealItem = new MealItem(meal, food, itemRequestDTO.quantity());
        meal.getMealItems().add(mealItem);
        mealRepository.save(meal);
    }

    public MealResponseDTO getMealDetails(UUID user_id, UUID meal_id) {
        var meal = getMealForUser(user_id, meal_id);
        return mealMapperDTO.apply(meal);
    }

    private Meal getMealForUser(UUID user_id, UUID meal_id) {
        var meal = mealRepository.findById(meal_id)
                .orElseThrow(() -> new RuntimeException("Meal not found"));
        var user = userService.getUser(user_id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(!user.equals(meal.getUser())) {
            throw new RuntimeException("Meal does not belong to this user");
        }
        return meal;
    }

    public void updateMeal(UUID user_id, UUID meal_id, UUID meal_item_id, ItemRequestDTO itemRequestDTO) {
        var meal = getMealForUser(user_id, meal_id);
        var mealItemDb = meal.getMealItems()
                .stream()
                .filter(mealItem -> mealItem.getId().equals(meal_item_id))
                .findAny();
        if (mealItemDb.isEmpty()) {
            throw new RuntimeException("Meal item does not exist");
        }
        var mealItem = mealItemDb.get();
        mealItem.setQuantity(itemRequestDTO.quantity());
        mealItem.calculateNutritionalValues();
        mealRepository.save(meal);
    }
}
