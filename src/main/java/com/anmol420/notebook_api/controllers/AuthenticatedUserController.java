package com.anmol420.notebook_api.controllers;

import com.anmol420.notebook_api.config.JwtUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/authenticated")
@RequiredArgsConstructor
public class AuthenticatedUserController {

    private final JwtUtils jwtUtils;

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
        String token = null;
        for (Cookie cookie: request.getCookies()) {
            if ("jwt".equals(cookie.getName())) {
                token = cookie.getValue();
            }
        }

        if (null == token) {
            return ResponseEntity.status(401).body("Unauthenticated!");
        }

        String username = jwtUtils.extractUsername(token);
        if (null == username) {
            return ResponseEntity.status(401).body("Invalid or Expired Token");
        }

        return ResponseEntity.ok("Hello " + username);
    }

}
