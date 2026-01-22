package com.cropmonitoring.controller;

import com.cropmonitoring.config.JwtUtil;
import com.cropmonitoring.entity.User;
import com.cropmonitoring.repository.UserRepository;
import com.cropmonitoring.util.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository,
                          JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    // REGISTER (FARMER)
    @PostMapping("/register")
    public ApiResponse register(@RequestBody User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            return new ApiResponse(false,
                    "Email already exists", null);
        }

        user.setRole("FARMER"); // 🔥 FORCE ROLE
        userRepository.save(user);

        return new ApiResponse(true,
                "User registered successfully", user);
    }

    // LOGIN
    @PostMapping("/login")
    public ApiResponse login(@RequestBody User request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid email"));

        if (!user.getPassword()
                .equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole()
        );

        return new ApiResponse(true,
                "Login successful", token);
    }
}
