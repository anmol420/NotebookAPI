package com.anmol420.notebook_api.controllers;

import com.anmol420.notebook_api.config.JwtUtils;
import com.anmol420.notebook_api.domain.dtos.RegisterRequest;
import com.anmol420.notebook_api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtUtils jwtUtils;

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest registerRequest
    ) {
        authService.register(registerRequest);
        return ResponseEntity.status(200).body("Registered!");
    }
}
