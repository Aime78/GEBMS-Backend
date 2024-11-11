package com.gebms.gebmsbackend.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class ExpenditureRequest {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "requested_date")
    private String requestedDate;
    private String department;
    @Column(name = "requested_by")
    private String requestedBy;
    @Column(name = "expenditure_category")
    private String expenditureCategory;
    private String subcategory;
    @Column(name = "requested_amount")
    private BigDecimal requestedAmount;
    @Column(name = "project_name")
    private String projectName;
    private String status;
    private String purpose;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(String requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public String getExpenditureCategory() {
        return expenditureCategory;
    }

    public void setExpenditureCategory(String expenditureCategory) {
        this.expenditureCategory = expenditureCategory;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(BigDecimal requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    // Generate UUID before persisting the entity
    @PrePersist
    public void generateUUID() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
