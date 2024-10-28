package com.arthurbf.CalorieTrackerApp.dtos;

import java.util.UUID;

public record ItemResponseDTO (UUID id, String foodName, double quantity, double calories, double proteins, double fats, double carbs) {}
