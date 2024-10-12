package com.arthurbf.CalorieTrackerApp.controllers;


import com.arthurbf.CalorieTrackerApp.dtos.RegistrationDTO;
import com.arthurbf.CalorieTrackerApp.dtos.UserResponseDTO;
import com.arthurbf.CalorieTrackerApp.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<RegistrationDTO> registerUser(@RequestBody RegistrationDTO userDTO) {
        userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }
}
