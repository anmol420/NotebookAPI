package com.anmol420.notebook_api.controllers;

import com.anmol420.notebook_api.utils.ExtractAuthenticatedUUID;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/authenticated")
@RequiredArgsConstructor
public class AuthenticatedUserController {

    private final ExtractAuthenticatedUUID extractAuthenticatedUUID;

    @PostMapping(path = "/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.status(200).body("Logged Out!");
    }

    @GetMapping("/dashboard")
    public ResponseEntity<?> dashboard(HttpServletRequest request) {
        UUID id = extractAuthenticatedUUID.extractUUID(request);
        if (null == id) {
            return ResponseEntity.status(500).body("Error!");
        }
        return ResponseEntity.ok("Hello " + id);
    }

}
