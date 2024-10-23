package com.arthurbf.CalorieTrackerApp.dtos;

import java.util.UUID;

public record LoginResponseDTO(UUID userId, String token) {
}
