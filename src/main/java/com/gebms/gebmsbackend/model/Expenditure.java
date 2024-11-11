package com.gebms.gebmsbackend.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Expenditure {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private int year;
    private String department;
    @Column(name = "expenditure_category")
    private String expenditureCategory;
    private String subcategory;
    @Column(name = "amount_spent")
    private BigDecimal amountSpent;
    @Column(name = "project_name")
    private String projectName;

    public Expenditure() {}

    public Expenditure(int year, String department, String expenditureCategory,
                       String subcategory, BigDecimal amountSpent,
                       String projectName){
        this.year = year;
        this.department = department;
        this.expenditureCategory = expenditureCategory;
        this.subcategory = subcategory;
        this.amountSpent = amountSpent;
        this.projectName = projectName;
        }

        // Getters and Setters
        public UUID getId() {
            return id;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
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

        public BigDecimal getAmountSpent() {
            return amountSpent;
        }

        public void setAmountSpent(BigDecimal amountSpent) {
            this.amountSpent = amountSpent;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        // Generate UUID before persisting the entity
        @PrePersist
        public void generateUUID() {
            if (id == null) {
                id = UUID.randomUUID();
            }
        }
}
