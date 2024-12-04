package com.gebms.gebmsbackend.repository;

import com.gebms.gebmsbackend.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {

}
