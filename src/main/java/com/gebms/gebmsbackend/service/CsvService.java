//package com.gebms.gebmsbackend.service;
//
//import com.gebms.gebmsbackend.model.ExpenditureRequest;
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVParser;
//import org.apache.commons.csv.CSVRecord;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.Reader;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Service
//public class CsvService {
//
//    private static final Logger logger = LoggerFactory.getLogger(CsvService.class);
//
//    public List<ExpenditureRequest> readCsvFile(String filePath) {
//        List<ExpenditureRequest> expenditureRequest = new ArrayList<>();
//
//        try (Reader reader = new FileReader(filePath);
//             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
//
//            for (CSVRecord record : csvParser) {
//                ExpenditureRequest request = new ExpenditureRequest();
//                request.setRequestedDate(record.get("Request Date"));
//                request.setDepartment(record.get("Department"));
//                request.setRequestedBy(record.get("Requested By"));
//                request.setExpenditureCategory(record.get("Expenditure Category"));
//                request.setSubcategory(record.get("Subcategory"));
//                request.setRequestedAmount(new BigDecimal(record.get("Requested Amount ($)").replace(",", "")));
//                request.setProjectName(record.get("Project Name"));
//                request.setStatus(record.get("Status"));
//                request.setPurpose(record.get("Purpose"));
//
//                expenditureRequest.add(request);
//
//                logger.info("Parsed record: {}", expenditureRequest);
//            }
//        } catch (IOException e) {
//            logger.error("Error reading CSV file: {}", e.getMessage(), e);
//        }
//
//        return expenditureRequest;
//    }
//}

//
//@Service
//public class CsvService {
//
//    private static final Logger logger = LoggerFactory.getLogger(CsvService.class);
//
//    public List<Expenditure> readCsvFile(String filePath) {
//        List<Expenditure> expenditures = new ArrayList<>();
//
//        try (Reader reader = new FileReader(filePath);
//             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
//
//            for (CSVRecord record : csvParser) {
//                Expenditure expenditure = new Expenditure(
//                        Integer.parseInt(record.get("Year")),
//                        record.get("Department"),
//                        record.get("Expenditure Category"),
//                        record.get("Subcategory"),
//                        new BigDecimal(record.get("Amount Spent ($)").replace(",", "")),
//                        record.get("Project Name")
//                );
//                expenditures.add(expenditure);
//                logger.info("Parsed record: {}", expenditure);
//            }
//        } catch (IOException e) {
//            logger.error("Error reading CSV file: {}", e.getMessage(), e);
//        }
//
//        return expenditures;
//    }
//}

