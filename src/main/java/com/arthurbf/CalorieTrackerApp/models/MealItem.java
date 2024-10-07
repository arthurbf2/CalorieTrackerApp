package com.arthurbf.CalorieTrackerApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@SuppressWarnings("FieldCanBeLocal")
@Entity
@Table(name ="TB_MEAL_ITEMS")
public class MealItem {

    public MealItem(Meal meal, Food food, double servingSize) {
        this.meal = meal;
        this.food = food;
        this.servingSize = servingSize;
        calculateNutritionalValues();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "meal_id", nullable = false)
    private Meal meal;

    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    @NotNull
    private double servingSize;

    @NotNull
    private double calories;

    private double proteins;

    private double fats;

    private double carbs;

    public void calculateNutritionalValues() {
        this.calories = food.getCaloriesPerServing() * servingSize;
        this.proteins = food.getProteinsPerServing() * servingSize;
        this.fats = food.getFatsPerServing() * servingSize;
        this.carbs = food.getCarbsPerServing() * servingSize;
    }
}
