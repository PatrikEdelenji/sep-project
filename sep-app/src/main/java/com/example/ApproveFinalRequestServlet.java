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

@WebServlet("/approveFinalRequest")
public class ApproveFinalRequestServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String clientRecord = request.getParameter("clientRecord");

        String projectRoot = System.getProperty("user.dir");
        String originalFilePath = projectRoot + "/data/approved_fm.csv";
        String approvedFilePath = projectRoot + "/data/approved_admin.csv";

        List<String[]> allRecords = new ArrayList<>();
        
        
        try (CSVReader csvReader = new CSVReader(new FileReader(originalFilePath))) {
            String[] record;
            while ((record = csvReader.readNext()) != null) {
                if (record[0].equals(clientRecord)) {
                    writeRecordToCsv(approvedFilePath, record);  
                }
                allRecords.add(record);  
            }
        } catch (CsvValidationException ex) {
            ex.printStackTrace();  
        }

        
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(originalFilePath))) {
            csvWriter.writeAll(allRecords);
        }

        
        response.sendRedirect("/viewFMApprovedRequests");
    }

    private void writeRecordToCsv(String filePath, String[] record) throws IOException {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(filePath, true))) {
            csvWriter.writeNext(record);
        }
    }
}