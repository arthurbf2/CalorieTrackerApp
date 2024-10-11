package com.arthurbf.CalorieTrackerApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

@SuppressWarnings("FieldCanBeLocal")
@Entity
@Table(name ="TB_MEAL_ITEMS")
public class MealItem {

    public MealItem() {}

    public MealItem(Meal meal, Food food, double quantity) {
        this.meal = meal;
        this.food = food;
        this.quantity = quantity;
        calculateNutritionalValues();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "meal_id", nullable = false)
    @JsonIgnore
    private Meal meal;

    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    @NotNull
    @Positive
    private double quantity;

    @NotNull
    private double calories;

    private double proteins;

    private double fats;

    private double carbs;

    public void calculateNutritionalValues() {
        this.calories = (food.getCaloriesPerServing() / 100) * this.quantity;
        this.proteins = (food.getProteinsPerServing() / 100) * this.quantity;
        this.fats = (food.getFatsPerServing() / 100) * this.quantity;
        this.carbs = (food.getCarbsPerServing() / 100) * this.quantity;
    }

    public void setQuantity(@NotNull @Positive double quantity) {
        this.quantity = quantity;
    }

    public Meal getMeal() {
        return meal;
    }

    public Food getFood() {
        return food;
    }

    @NotNull
    @Positive
    public double getQuantity() {
        return quantity;
    }

    @NotNull
    public double getCalories() {
        return calories;
    }

    public double getProteins() {
        return proteins;
    }

    public double getFats() {
        return fats;
    }

    public double getCarbs() {
        return carbs;
    }
}
