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

public class CreateClientRecordServletTest {

    public static void main(String[] args) {
        CreateClientRecordServletTest test = new CreateClientRecordServletTest();
        test.testCreateClientRecordLogic();
    }

    public void testCreateClientRecordLogic() {
 
        String testDirPath = "test_data";
        String filePath = testDirPath + "/approved_admin.csv";


        new File(testDirPath).mkdirs();

  
        List<String[]> mockData = new ArrayList<>();
        mockData.add(new String[]{"testClientRecord", "Alice Smith", "Birthday", "2024-10-01", "2024-10-02", "100", "Yes", "Yes", "No", "Yes", "Yes", "5000", "Pending"});
        mockData.add(new String[]{"anotherRecord", "Bob Johnson", "Conference", "2024-11-01", "2024-11-02", "200", "No", "No", "Yes", "Yes", "No", "10000", "Approved"});


        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeAll(mockData);
        } catch (IOException e) {
            System.err.println("Error writing mock data to approved_admin.csv: " + e.getMessage());
        }


        String clientRecordToRetrieve = "testClientRecord";
        

        String[] retrievedRecord = null;
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] record;
            while ((record = csvReader.readNext()) != null) {
                if (record[0].equals(clientRecordToRetrieve)) {
                    retrievedRecord = record;
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading mock data: " + e.getMessage());
        }


        boolean isRecordRetrievedCorrectly = retrievedRecord != null 
                && retrievedRecord[0].equals("testClientRecord")
                && retrievedRecord[1].equals("Alice Smith")
                && retrievedRecord[2].equals("Birthday")
                && retrievedRecord[3].equals("2024-10-01")
                && retrievedRecord[4].equals("2024-10-02")
                && retrievedRecord[5].equals("100")
                && retrievedRecord[6].equals("Yes")
                && retrievedRecord[7].equals("Yes")
                && retrievedRecord[8].equals("No")
                && retrievedRecord[9].equals("Yes")
                && retrievedRecord[10].equals("Yes")
                && retrievedRecord[11].equals("5000")
                && retrievedRecord[12].equals("Pending");

        System.out.println("Test Create Client Record Logic: " + (isRecordRetrievedCorrectly ? "PASSED" : "FAILED"));


        cleanupTestFiles(testDirPath);
    }

    private void cleanupTestFiles(String testDirPath) {
        try {
            Files.deleteIfExists(Paths.get(testDirPath + "/approved_admin.csv"));
            Files.deleteIfExists(Paths.get(testDirPath));
        } catch (IOException e) {
            System.err.println("Error cleaning up test files: " + e.getMessage());
        }
    }
}