package com.gebms.gebmsbackend.service;

import com.gebms.gebmsbackend.model.Expenditure;
import com.gebms.gebmsbackend.model.ExpenditureRequest;
import com.gebms.gebmsbackend.repository.ExpenditureRepository;
import com.gebms.gebmsbackend.repository.ExpenditureRequestRepository;
import javafx.application.Platform;
import javafx.stage.FileChooser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.function.Function;


@Component
public class CsvProcessor implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(CsvProcessor.class);

    @Autowired
    private CsvService csvService;

    @Autowired
    private ExpenditureRequestRepository expenditureRequestRepository;

    @Override
    public void run(String... args) {
        logger.info("Starting CSV processing...");

        CountDownLatch latch = new CountDownLatch(1);

        Platform.startup(() -> {
            Platform.runLater(() -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select CSV File");
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("CSV Files", "*.csv")
                );

                File selectedFile = fileChooser.showOpenDialog(null);

                if (selectedFile != null) {
                    String filePath = selectedFile.getAbsolutePath();
                    new Thread(() -> {
                        try {
                            List<ExpenditureRequest> expenditureRequests = csvService.readCsvFile(filePath);
                            logger.info("CSV processing completed. {} records parsed.", expenditureRequests.size());

                            // Save all Expenditure objects to the database
                            expenditureRequestRepository.saveAll(expenditureRequests);
                            logger.info("All records saved to the database.");
                        } catch (Exception e) {
                            logger.error("Error processing CSV file: {}", e.getMessage(), e);
                        } finally {
                            latch.countDown();
                            Platform.exit();
                        }
                    }).start();
                } else {
                    logger.info("No file selected. CSV processing cancelled.");
                    latch.countDown();
                    Platform.exit();
                }
            });
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            logger.error("Interrupted while waiting for CSV processing to complete", e);
        }
    }
}

//
//@Component
//public class CsvProcessor implements CommandLineRunner {
//    private static final Logger logger = LoggerFactory.getLogger(CsvProcessor.class);
//
//    @Autowired
//    private CsvService csvService;
//
//    @Autowired
//    private ExpenditureRepository expenditureRepository;
//
//    @Override
//    public void run(String... args) {
//        logger.info("Starting CSV processing...");
//
//        CountDownLatch latch = new CountDownLatch(1);
//
//        Platform.startup(() -> {
//            Platform.runLater(() -> {
//                FileChooser fileChooser = new FileChooser();
//                fileChooser.setTitle("Select CSV File");
//                fileChooser.getExtensionFilters().add(
//                        new FileChooser.ExtensionFilter("CSV Files", "*.csv")
//                );
//
//                File selectedFile = fileChooser.showOpenDialog(null);
//
//                if (selectedFile != null) {
//                    String filePath = selectedFile.getAbsolutePath();
//                    new Thread(() -> {
//                        try {
//                            List<Expenditure> expenditures = csvService.readCsvFile(filePath);
//                            logger.info("CSV processing completed. {} records parsed.", expenditures.size());
//
//                            // Save all Expenditure objects to the database
//                            expenditureRepository.saveAll(expenditures);
//                            logger.info("All records saved to the database.");
//                        } catch (Exception e) {
//                            logger.error("Error processing CSV file: {}", e.getMessage(), e);
//                        } finally {
//                            latch.countDown();
//                            Platform.exit();
//                        }
//                    }).start();
//                } else {
//                    logger.info("No file selected. CSV processing cancelled.");
//                    latch.countDown();
//                    Platform.exit();
//                }
//            });
//        });
//
//        try {
//            latch.await();
//        } catch (InterruptedException e) {
//            logger.error("Interrupted while waiting for CSV processing to complete", e);
//        }
//    }
//}
//
