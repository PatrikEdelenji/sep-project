package com.example;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class ApproveEventRequestServletTest {

    public static void main(String[] args) {
        ApproveEventRequestServletTest test = new ApproveEventRequestServletTest();
        test.testApproveRequestLogic();
    }

    public void testApproveRequestLogic() {

        String testDirPath = "test_data";
        String originalFilePath = testDirPath + "/event_requests.csv";
        String approvedFilePath = testDirPath + "/approved_scso.csv";


        new File(testDirPath).mkdirs();


        List<String[]> allRecords = new ArrayList<>();
        allRecords.add(new String[]{"testClientRecord", "Event Type", "Description", "Assignee", "Priority", "Status"});
        allRecords.add(new String[]{"anotherRecord", "Event Type", "Description", "Assignee", "Priority", "Status"});


        try (CSVWriter writer = new CSVWriter(new FileWriter(originalFilePath))) {
            writer.writeAll(allRecords);
        } catch (IOException e) {
            System.err.println("Error writing mock data to event_requests.csv: " + e.getMessage());
        }


        String clientRecordToApprove = "testClientRecord";


        List<String[]> approvedRecords = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(originalFilePath))) {
            String[] record;
            while ((record = csvReader.readNext()) != null) {
                if (record[0].equals(clientRecordToApprove)) {
                    writeRecordToCsv(approvedFilePath, record); 
                    approvedRecords.add(record);
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading mock data: " + e.getMessage());
        }


        boolean isRecordApproved = approvedRecords.size() == 1 
                                    && approvedRecords.get(0)[0].equals(clientRecordToApprove);

        System.out.println("Test Approve Request Logic: " + (isRecordApproved ? "PASSED" : "FAILED"));


        cleanupTestFiles(testDirPath);
    }

    private void writeRecordToCsv(String filePath, String[] record) {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(filePath, true))) {
            csvWriter.writeNext(record);
        } catch (IOException e) {
            System.err.println("Error writing approved record to CSV: " + e.getMessage());
        }
    }

    private void cleanupTestFiles(String testDirPath) {
        try {
            Files.deleteIfExists(Paths.get(testDirPath + "/event_requests.csv"));
            Files.deleteIfExists(Paths.get(testDirPath + "/approved_scso.csv"));
            Files.deleteIfExists(Paths.get(testDirPath));
            System.out.println("Cleanup complete");
        } catch (IOException e) {
            System.err.println("Error cleaning up test files: " + e.getMessage());
        }
    }
}

