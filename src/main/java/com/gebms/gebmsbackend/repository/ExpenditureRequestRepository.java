package com.gebms.gebmsbackend.repository;

import com.gebms.gebmsbackend.model.ExpenditureRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ExpenditureRequestRepository extends JpaRepository<ExpenditureRequest, UUID> {
    List<ExpenditureRequest> findByDepartmentIgnoreCase(String department);
    @Query("SELECT e.department, SUM(e.requestedAmount) AS totalAmount " +
            "FROM ExpenditureRequest e " +
            "GROUP BY e.department")
    List<Object[]> findTotalExpendituresByDepartment();
}
