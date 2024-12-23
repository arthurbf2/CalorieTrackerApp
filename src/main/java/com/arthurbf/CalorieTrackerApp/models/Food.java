package com.arthurbf.CalorieTrackerApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;



@Entity
@Table(name = "TB_FOODS")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    private String name;

    // 1 serving = 100 grams
    @NotNull
    private double caloriesPerServing;

    private double proteinsPerServing;

    private double carbsPerServing;

    private double fatsPerServing;

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    @NotNull
    public double getCaloriesPerServing() {
        return caloriesPerServing;
    }

    public void setCaloriesPerServing(@NotNull double caloriesPerServing) {
        this.caloriesPerServing = caloriesPerServing;
    }

    public double getProteinsPerServing() {
        return proteinsPerServing;
    }

    public void setProteinsPerServing(double proteinsPerServing) {
        this.proteinsPerServing = proteinsPerServing;
    }

    public double getCarbsPerServing() {
        return carbsPerServing;
    }

    public void setCarbsPerServing(double carbsPerServing) {
        this.carbsPerServing = carbsPerServing;
    }

    public double getFatsPerServing() {
        return fatsPerServing;
    }

    public void setFatsPerServing(double fatsPerServing) {
        this.fatsPerServing = fatsPerServing;
    }
}
