package com.gebms.gebmsbackend.controller;

import com.gebms.gebmsbackend.dto.UserRequest;
import com.gebms.gebmsbackend.model.Department;
import com.gebms.gebmsbackend.model.User;
import com.gebms.gebmsbackend.model.UserRole;
import com.gebms.gebmsbackend.repository.DepartmentRepository;
import com.gebms.gebmsbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    @PostMapping
    String createUser(@RequestBody UserRequest userDetails) {
       Department department = departmentRepository.findById(UUID.fromString(userDetails.getDepartment())).orElseThrow(()-> new RuntimeException("Department not found"));
        User user = new User();
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setPhoneNumbers(userDetails.getPhoneNumbers());
        user.setRole(UserRole.valueOf(userDetails.getRole()));
        user.setPassword(userDetails.getPassword());
        user.setDepartment(department);

        userRepository.save(user);

        return "User created Successfully!";
    }

    @GetMapping
    List<User> getAllUsers() {
        return userRepository.findAll();

    }

    @DeleteMapping("/{id}")
    String deleteUser(@PathVariable UUID id) {
        userRepository.deleteById(id);
        return "User deleted Successfully!";
    }

}
