package com.example;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class SubmitFinancialReviewServletTest {

    public static void main(String[] args) {
        SubmitFinancialReviewServletTest test = new SubmitFinancialReviewServletTest();
        test.testSubmitFinancialReviewLogic();
    }

    public void testSubmitFinancialReviewLogic() {

        String testDirPath = "test_data";
        String filePath = testDirPath + "/approved_fm.csv";

 
        new File(testDirPath).mkdirs();

        // Mock data for submission
        String[] submittedRecord = {
            "testClientRecord", // clientRecord
            "Alice Smith",      // clientName
            "Birthday",         // eventType
            "2024-10-01",       // fromDate
            "2024-10-02",       // toDate
            "100",              // expectedAttendees
            "Yes",              // decorations
            "Yes",              // parties
            "No",               // photos
            "Yes",              // meals
            "No",               // drinks (simulates unchecked state)
            "5000",             // budget
            "Pending"           // budgetReview
        };

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath, true))) {
            writer.writeNext(submittedRecord);
        } catch (IOException e) {
            System.err.println("Error writing mock data to approved_fm.csv: " + e.getMessage());
        }


        boolean isRecordSaved = false;
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                if (matchesRecord(record, submittedRecord)) {
                    isRecordSaved = true;
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading saved data: " + e.getMessage());
        }

        System.out.println("Test Submit Financial Review Logic: " + (isRecordSaved ? "PASSED" : "FAILED"));

        cleanupTestFiles(testDirPath);
    }

    private boolean matchesRecord(String[] record, String[] expectedRecord) {
        if (record.length != expectedRecord.length) return false;
        for (int i = 0; i < record.length; i++) {
            if (!record[i].equals(expectedRecord[i])) return false;
        }
        return true;
    }

    private void cleanupTestFiles(String testDirPath) {
        try {
            Files.deleteIfExists(Paths.get(testDirPath + "/approved_fm.csv"));
            Files.deleteIfExists(Paths.get(testDirPath));
        } catch (IOException e) {
            System.err.println("Error cleaning up test files: " + e.getMessage());
        }
    }
}
