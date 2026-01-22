package com.cropmonitoring.controller;

import com.cropmonitoring.repository.CropRepository;
import com.cropmonitoring.repository.UserRepository;
import com.cropmonitoring.util.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    private final CropRepository cropRepository;
    private final UserRepository userRepository;

    public AdminController(CropRepository cropRepository,
                           UserRepository userRepository) {
        this.cropRepository = cropRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public ApiResponse getAllUsers() {
        return new ApiResponse(true,
                "All users fetched",
                userRepository.findAll());
    }

    @GetMapping("/crops")
    public ApiResponse getAllCrops() {
        return new ApiResponse(true,
                "All crops fetched",
                cropRepository.findAll());
    }
}
