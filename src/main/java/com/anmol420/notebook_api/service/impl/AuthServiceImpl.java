package com.anmol420.notebook_api.service.impl;

import com.anmol420.notebook_api.domain.dtos.user.LoginRequest;
import com.anmol420.notebook_api.domain.dtos.user.RegisterRequest;
import com.anmol420.notebook_api.domain.entities.User;
import com.anmol420.notebook_api.repository.UserRepository;
import com.anmol420.notebook_api.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void register(RegisterRequest request) {
        Optional<User> userFind = userRepository.findByUsername(request.getUsername());
        if (userFind.isEmpty()) {
            User user = User.builder()
                    .username(request.getUsername())
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            userRepository.save(user);
        }
    }

    @Override
    public LoginRequest login(LoginRequest request) {
        Optional<User> userFound = userRepository.findByUsername(request.getUsername());
        if (userFound.isPresent()) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            return LoginRequest.builder()
                    .username(request.getUsername())
                    .build();
        }
        return null;
    }

}
