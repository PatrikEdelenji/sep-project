package com.example;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
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


        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] record;
            while ((record = csvReader.readNext()) != null) {
              
                if (!record[0].equals(clientRecord)) {
                    updatedRecords.add(record);
                }
            }
        } catch (CsvValidationException ex) {
        }

        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(filePath))) {
            csvWriter.writeAll(updatedRecords);
        }

        response.sendRedirect("viewAllNewRequests");
    }
}