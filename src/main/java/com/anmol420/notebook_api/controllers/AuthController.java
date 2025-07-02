package com.anmol420.notebook_api.controllers;

import com.anmol420.notebook_api.config.JwtUtils;
import com.anmol420.notebook_api.domain.dtos.LoginRequest;
import com.anmol420.notebook_api.domain.dtos.RegisterRequest;
import com.anmol420.notebook_api.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest loginRequest,
            HttpServletResponse response
    ) {
        System.out.println("Hemlo1");
        LoginRequest user = authService.login(loginRequest);
        System.out.println("Hemlo5");
        if (null != user) {
            String token = jwtUtils.generateToken(user.getUsername());
            Cookie cookie = new Cookie("jwt", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(24*60*60*60);
            response.addCookie(cookie);

            return ResponseEntity.status(200).body("Logged In!");
        }
        return ResponseEntity.status(404).body("User Not Found!");
    }

}
