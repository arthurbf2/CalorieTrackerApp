package com.arthurbf.CalorieTrackerApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.arthurbf.CalorieTrackerApp.models.Food;
import java.util.UUID;

@Repository
public interface FoodRepository extends JpaRepository<Food, UUID> {
}
