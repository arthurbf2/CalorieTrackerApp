package com.arthurbf.CalorieTrackerApp.services;


import com.arthurbf.CalorieTrackerApp.dtos.UserRequestDTO;
import com.arthurbf.CalorieTrackerApp.dtos.UserResponseDTO;
import com.arthurbf.CalorieTrackerApp.models.User;
import com.arthurbf.CalorieTrackerApp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public void createUser(UserRequestDTO userDTO) {
        var userDb = userRepository.findByEmail(userDTO.email());
        if (userDb.isPresent()) {
            throw new RuntimeException("User already exists.");
        }
        var user = new User();
        BeanUtils.copyProperties(userDTO, user);
        userRepository.save(user);
    }

    public List<UserResponseDTO> getUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponseDTO(user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }

    public Optional<User> getUser(UUID user_id) {
        return userRepository.findById(user_id);
    }
}
