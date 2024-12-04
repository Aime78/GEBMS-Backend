package com.gebms.gebmsbackend.controller;

import com.gebms.gebmsbackend.dto.LoginResponse;
import com.gebms.gebmsbackend.model.User;
import com.gebms.gebmsbackend.repository.UserRepository;
import com.gebms.gebmsbackend.service.JwtUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController  {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // Register new user
    @PostMapping("/register")
    public String register(@RequestBody User userDetails) {
        if (userRepository.findByEmail(userDetails.getEmail()).isPresent()) {
            return "Email is already in use!";
        }

        User user = new User();
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setPhoneNumbers(userDetails.getPhoneNumbers());
        user.setRole(userDetails.getRole());
        user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        userRepository.save(user);

        return "User registered successfully!";
    }

    // Authenticate user
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody User userDetails) {
        try {
            Optional<User> userOptional = userRepository.findByEmail(userDetails.getEmail());

            if (userOptional.isPresent()) {
                User user = userOptional.get();

                if (passwordEncoder.matches(userDetails.getPassword(), user.getPassword())) {
                    String token = jwtUtil.jwtTokenGenerator(userDetails.getEmail());

                    LoginResponse response = new LoginResponse(
                            token,
                            "Login successful",
                            200
                    );

                    return ResponseEntity.ok(response);
                } else {
                    return ResponseEntity
                            .status(HttpStatus.UNAUTHORIZED)
                            .body(new LoginResponse(
                                    null,
                                    "Invalid credentials",
                                    401
                            ));
                }
            } else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new LoginResponse(
                                null,
                                "User not found",
                                404
                        ));
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new LoginResponse(
                            null,
                            "An error occurred during login",
                            500
                    ));
        }
    }

    // Logout User
    @PostMapping("/auth/logout")
    public String logout(HttpSession session) {
        // Invalidate the session if it exists
        if (session != null) {
            session.invalidate();
        }
        return "Logout sucessfully!";
    }
}
