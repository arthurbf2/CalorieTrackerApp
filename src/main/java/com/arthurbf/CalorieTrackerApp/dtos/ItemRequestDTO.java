package com.arthurbf.CalorieTrackerApp.dtos;

import java.util.UUID;

public record ItemRequestDTO(UUID food_id, double quantity) {
}
