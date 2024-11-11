package com.gebms.gebmsbackend.controller;

import com.gebms.gebmsbackend.repository.ExpenditureRepository;
import com.gebms.gebmsbackend.repository.ExpenditureRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ExpenditureSummaryController {
    private final ExpenditureRepository expenditureRepository;
    private static final Logger logger = LoggerFactory.getLogger(ExpenditureController.class);
    private final ExpenditureRequestRepository expenditureRequestRepository;

    public ExpenditureSummaryController(ExpenditureRepository expenditureRepository, ExpenditureRequestRepository expenditureRequestRepository) {
        this.expenditureRepository = expenditureRepository;
        this.expenditureRequestRepository = expenditureRequestRepository;
    }

    @GetMapping("/expenditure-summary")
    List<Object[]> getExpenditureSummary() {
        List<Object[]> expenditureSummary = expenditureRepository.findTotalExpendituresFor2023ByDepartment();
        return expenditureSummary;
    }

    @GetMapping("/expenditure-request-summary")
    List<Object[]> getExpenditureRequestSummary() {
        List<Object[]> expenditureRequestSummary = expenditureRequestRepository.findTotalExpendituresByDepartment();
        return expenditureRequestSummary;
    }
}


