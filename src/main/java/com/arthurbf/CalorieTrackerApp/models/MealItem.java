package com.arthurbf.CalorieTrackerApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

@SuppressWarnings("FieldCanBeLocal")
@Entity
@Table(name ="TB_MEAL_ITEMS")
public class MealItem {

    public MealItem(Meal meal, Food food, double servingQuantity) {
        this.meal = meal;
        this.food = food;
        this.servingQuantity = servingQuantity;
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
    @Positive
    private double servingQuantity;

    @NotNull
    private double calories;

    private double proteins;

    private double fats;

    private double carbs;

    public void calculateNutritionalValues() {
        this.calories = food.getCaloriesPerServing() * servingQuantity;
        this.proteins = food.getProteinsPerServing() * servingQuantity;
        this.fats = food.getFatsPerServing() * servingQuantity;
        this.carbs = food.getCarbsPerServing() * servingQuantity;
    }
}
