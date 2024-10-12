package com.arthurbf.CalorieTrackerApp.dtos;

import com.arthurbf.CalorieTrackerApp.models.Role;

public record RegistrationDTO(String name, String email, String password, Role role) {
}
