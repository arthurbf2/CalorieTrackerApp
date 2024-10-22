package com.arthurbf.CalorieTrackerApp.services;

import com.arthurbf.CalorieTrackerApp.dtos.RegistrationDTO;
import com.arthurbf.CalorieTrackerApp.dtos.UserResponseDTO;
import com.arthurbf.CalorieTrackerApp.models.Role;
import com.arthurbf.CalorieTrackerApp.models.User;
import com.arthurbf.CalorieTrackerApp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public void createUser(RegistrationDTO userDTO) {
        var userDb = userRepository.findByEmail(userDTO.email());
        if (userDb.isPresent()) {
            throw new RuntimeException("User already exists.");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());
        var user = new User();
        user.setEmail(userDTO.email());
        user.setName(userDTO.name());
        user.setPassword(encryptedPassword);
        user.setRole(userDTO.role() != null ? userDTO.role() : Role.USER);
        userRepository.save(user);
    }

    public List<UserResponseDTO> getUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponseDTO(user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }

    public User getUser(UUID user_id) {
        return userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
