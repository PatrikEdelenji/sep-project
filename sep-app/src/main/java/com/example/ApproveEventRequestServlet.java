package com.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.exceptions.CsvValidationException;

@WebServlet("/approveRequest")
public class ApproveEventRequestServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String clientRecord = request.getParameter("clientRecord");

        String projectRoot = System.getProperty("user.dir");
        String originalFilePath = projectRoot + "/data/event_requests.csv";
        String approvedFilePath = projectRoot + "/data/approved_scso.csv";

        List<String[]> allRecords = new ArrayList<>();
        
        // Read existing records and find the one to approve
        try (CSVReader csvReader = new CSVReader(new FileReader(originalFilePath))) {
            String[] record;
            while ((record = csvReader.readNext()) != null) {
                if (record[0].equals(clientRecord)) {
                    record = appendApprovalStatus(record, "Approved");
                    writeRecordToCsv(approvedFilePath, record);  // Write approved record to approved_scso.csv
                }
                allRecords.add(record);  // Update list with the new status for all records
            }
        } catch (CsvValidationException ex) {
        }

        // Update the original file to reflect approval status
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(originalFilePath))) {
            csvWriter.writeAll(allRecords);
        }

        response.sendRedirect("viewAllNewRequests.jsp");
    }

    private String[] appendApprovalStatus(String[] record, String status) {
        String[] updatedRecord = new String[record.length + 1];
        System.arraycopy(record, 0, updatedRecord, 0, record.length);
        updatedRecord[record.length] = status;  // Append the approval status
        return updatedRecord;
    }

    private void writeRecordToCsv(String filePath, String[] record) throws IOException {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(filePath, true))) {
            csvWriter.writeNext(record);
        }
    }
}