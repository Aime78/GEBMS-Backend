package com.gebms.gebmsbackend.repository;

import com.gebms.gebmsbackend.model.Expenditure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ExpenditureRepository extends JpaRepository<Expenditure, UUID> {
    List<Expenditure> findByDepartmentIgnoreCaseOrderByYearDesc(String department);
    List<Expenditure> findByYear(int year);
    @Query("SELECT e.department, SUM(e.amountSpent) AS expenditures " +
            "FROM Expenditure e " +
            "WHERE e.year = 2023 " +
            "GROUP BY e.department")
    List<Object[]> findTotalExpendituresFor2023ByDepartment();
}
