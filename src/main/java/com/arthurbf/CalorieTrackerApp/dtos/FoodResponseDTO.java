package com.arthurbf.CalorieTrackerApp.dtos;

import java.util.UUID;

public record FoodResponseDTO(String name, UUID id, double calories) {
}
