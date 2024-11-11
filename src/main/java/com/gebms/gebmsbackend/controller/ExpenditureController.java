package com.gebms.gebmsbackend.controller;

import com.gebms.gebmsbackend.model.Expenditure;
import com.gebms.gebmsbackend.repository.ExpenditureRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
public class ExpenditureController {
    private final ExpenditureRepository expenditureRepository;
    private static final Logger logger = LoggerFactory.getLogger(ExpenditureController.class);

    public ExpenditureController(ExpenditureRepository expenditureRepository) {
        this.expenditureRepository = expenditureRepository;
    }
//
//    @GetMapping("/expenditure")
//    List<Expenditure> expenditureList(@RequestParam(required = false) String department) {
//        if (department != null && !department.isEmpty()) {
//            List<Expenditure> filteredExpenditures = expenditureRepository.findByDepartmentIgnoreCase(department);
//            logger.info("Fetching expenditures for department: {}", department);
//            logger.info("Found {} expenditures for department: {}", filteredExpenditures.size(), department);
//            return filteredExpenditures;
//        } else {
//            logger.info("Fetching all expenditures");
//            List<Expenditure> allExpenditures = expenditureRepository.findAll();
//            logger.info("Found {} expenditures", allExpenditures.size());
//            return allExpenditures;
//        }
//
//    }


    @GetMapping("/expenditure")
    List<Expenditure> expenditureList(
            @RequestParam(required = false) String department,
            @RequestParam(required = false) Integer year
    ) {
        List<Expenditure> expenditures = expenditureRepository.findAll();

        if (department != null && !department.isEmpty()) {
            if (year != null) {
                logger.info("Fetching expenditures for department: {} and year: {}", department, year);
            } else {
                expenditures = expenditureRepository.findByDepartmentIgnoreCaseOrderByYearDesc(department);
                logger.info("Fetching expenditures for department: {}", department);
            }
        } else if (year != null) {
            expenditures = expenditureRepository.findByYear(year);
            logger.info("Fetching expenditures for year: {}", year);
        } else {
            expenditures = expenditureRepository.findAll();
            logger.info("Fetching all expenditures");
        }

        logger.info("Found {} expenditures", expenditures.size());
        return expenditures;
    }

}
