package com.anmol420.notebook_api.utils;

import com.anmol420.notebook_api.config.JwtUtils;
import com.anmol420.notebook_api.domain.entities.User;
import com.anmol420.notebook_api.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class ExtractAuthenticatedUUID {

    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    public UUID extractUUID(HttpServletRequest request) {
        String token = null;
        for (Cookie cookie: request.getCookies()) {
            if ("jwt".equals(cookie.getName())) {
                token = cookie.getValue();
            }
        }
        if (null == token) {
            return null;
        }
        String username = jwtUtils.extractUsername(token);
        if (null == username) {
            return null;
        }
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(User::getId).orElse(null);
    }

}
