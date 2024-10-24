package com.arthurbf.CalorieTrackerApp.models;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Entity
@Table(name = "TB_MEALS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "date", "meal_type"})
})
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate date;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<MealItem> mealItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    public enum MealType {
        BREAKFAST,
        LUNCH,
        DINNER,
        SNACK
    }

    public UUID getId() {
        return id;
    }

    private double calculateTotal(Function<MealItem, Double> mapper) {
        double total = mealItems.stream()
                .mapToDouble(mapper::apply)
                .sum();
        return Math.round(total);
    }

    public double calculateTotalCalories() {
        return calculateTotal(MealItem::getCalories);
    }

    public double calculateTotalCarbs() {
        return calculateTotal(MealItem::getCarbs);
    }

    public double calculateTotalProtein() {
        return calculateTotal(MealItem::getProteins);
    }

    public double calculateTotalFat() {
        return calculateTotal(MealItem::getFats);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<MealItem> getMealItems() {
        return mealItems;
    }

    public void setMealItems(List<MealItem> mealItems) {
        this.mealItems = mealItems;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }
}
