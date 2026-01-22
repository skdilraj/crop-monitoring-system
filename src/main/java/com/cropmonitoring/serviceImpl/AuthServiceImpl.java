package com.cropmonitoring.serviceImpl;


import com.cropmonitoring.entity.User;
import com.cropmonitoring.repository.UserRepository;
import com.cropmonitoring.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        user.setRole("FARMER"); // default role
        return userRepository.save(user);
    }
}