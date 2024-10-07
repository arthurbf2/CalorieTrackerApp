package com.arthurbf.CalorieTrackerApp.models;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_MEALS")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private User user;

    private LocalDate date;

    @OneToMany(mappedBy = "meal")
    private List<MealItem> mealItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    public enum MealType {
        BREAKFAST,
        LUNCH,
        DINNER,
        SNACK
    }

}
