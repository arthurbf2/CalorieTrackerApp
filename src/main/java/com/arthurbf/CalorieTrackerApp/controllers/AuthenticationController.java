package com.arthurbf.CalorieTrackerApp.controllers;

import com.arthurbf.CalorieTrackerApp.dtos.AuthenticationDTO;
import com.arthurbf.CalorieTrackerApp.dtos.LoginResponseDTO;
import com.arthurbf.CalorieTrackerApp.dtos.RegistrationDTO;
import com.arthurbf.CalorieTrackerApp.models.User;
import com.arthurbf.CalorieTrackerApp.security.TokenService;
import com.arthurbf.CalorieTrackerApp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, UserService userService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegistrationDTO data) {
        userService.createUser(data);
        return ResponseEntity.ok().build();
    }
}
