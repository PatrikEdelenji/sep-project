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

@WebServlet("/rejectRequest")
public class RejectEventRequestServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String clientRecord = request.getParameter("clientRecord");

        String projectRoot = System.getProperty("user.dir");
        String filePath = projectRoot + "/data/event_requests.csv";

        List<String[]> updatedRecords = new ArrayList<>();

        // Read all records, filtering out the one to reject
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] record;
            while ((record = csvReader.readNext()) != null) {
                // Only add records that do not match the rejected clientRecord
                if (!record[0].equals(clientRecord)) {
                    updatedRecords.add(record);
                }
            }
        } catch (CsvValidationException ex) {
        }

        // Write the updated list back to the file, effectively removing the rejected record
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(filePath))) {
            csvWriter.writeAll(updatedRecords);
        }

        // Redirect to the servlet that reloads the table with updated data
        response.sendRedirect("viewAllNewRequests");
    }
}