package com.gebms.gebmsbackend.controller;

import com.gebms.gebmsbackend.model.Department;
import com.gebms.gebmsbackend.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentRepository departmentRepository;
    @PostMapping
    String createDepartment(@RequestBody Department departmentDetails) {
        departmentRepository.save(departmentDetails);
        return "Department created";
    }

    @GetMapping
    List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
}
