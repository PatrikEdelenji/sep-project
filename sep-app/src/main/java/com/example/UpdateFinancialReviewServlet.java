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

@WebServlet("/updateBudgetReview")
public class UpdateFinancialReviewServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String clientRecord = request.getParameter("clientRecord");
        String budgetReview = request.getParameter("budgetDescription");

        String projectRoot = System.getProperty("user.dir");
        String filePath = projectRoot + "/data/approved_scso.csv";

        List<String[]> records = new ArrayList<>();
        boolean recordUpdated = false;

        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] record;
            while ((record = csvReader.readNext()) != null) {
                if (record[0].equals(clientRecord)) { 
                    record[12] = budgetReview;
                    recordUpdated = true;
                }
                records.add(record); 
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServletException("Error reading financial review data", e);
        } catch (CsvValidationException ex) {
        }


        if (recordUpdated) {
            try (CSVWriter csvWriter = new CSVWriter(new FileWriter(filePath))) {
                csvWriter.writeAll(records); 
            } catch (IOException e) {
                e.printStackTrace();
                throw new ServletException("Error updating financial review data", e);
            }
        }


        response.sendRedirect("/viewSCSOApprovedRequests");
    }
}
