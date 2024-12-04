package com.gebms.gebmsbackend.controller;

import com.gebms.gebmsbackend.model.ExpenditureRequest;
import com.gebms.gebmsbackend.repository.ExpenditureRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExpenditureRequestController {
    private final ExpenditureRequestRepository expenditureRequestRepository;
    private static final Logger logger = LoggerFactory.getLogger(ExpenditureRequestController.class);

    public ExpenditureRequestController(ExpenditureRequestRepository expenditureRequestRepository) {
        this.expenditureRequestRepository = expenditureRequestRepository;
    }

    @GetMapping("/expenditure-request")
    List<ExpenditureRequest> expenditureRequestList(@RequestParam(required = false) String department) {
        if (department != null && !department.isEmpty()) {
            List<ExpenditureRequest> filteredExpenditures = expenditureRequestRepository.findByDepartmentIgnoreCase(department);
            logger.info("Fetching expenditures for department: {}", department);
            logger.info("Found {} expenditures for department: {}", filteredExpenditures.size(), department);
            return filteredExpenditures;
        } else {
            logger.info("Fetching all expenditures");
            List<ExpenditureRequest> allExpenditures = expenditureRequestRepository.findAll();
            logger.info("Found {} expenditures", allExpenditures.size());
            return allExpenditures;
        }
//        logger.info("Fetching all expenditure requests");
//        List<ExpenditureRequest> expenditureRequests = expenditureRequestRepository.findAll();
//        logger.info("Found {} expenditure requests", expenditureRequests.size());
//        return expenditureRequests;
    }
}
