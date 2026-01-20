package com.ops.platform.auth.service;

import com.ops.platform.auth.dto.LoginRequest;
import com.ops.platform.auth.dto.LoginResponse;
import com.ops.platform.auth.dto.SignUpRequest;
import com.ops.platform.auth.entity.User;
import com.ops.platform.auth.repository.UserRepository;
import com.ops.platform.auth.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtProvider.generateToken(user.getId(), user.getEmail(), user.getRole());

        return new LoginResponse(
                token,
                "Bearer",
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }

    public LoginResponse signUp(SignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole("OPERATOR");
        user.setStatus("ACTIVE");

        User savedUser = userRepository.save(user);

        String token = jwtProvider.generateToken(savedUser.getId(), savedUser.getEmail(), savedUser.getRole());

        return new LoginResponse(
                token,
                "Bearer",
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }

    public boolean validateToken(String token) {
        return jwtProvider.validateToken(token);
    }

    public String getUserEmailFromToken(String token) {
        return jwtProvider.getEmailFromJwt(token);
    }
}
