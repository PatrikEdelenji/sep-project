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

@WebServlet("/viewSCSOApprovedRequests")
public class ViewFinancialManagerEventRequests extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        System.out.println("We reached ViewFinancialManagerEventRequests");
        List<String[]> requests = new ArrayList<>();
        String projectRoot = System.getProperty("user.dir");
        String filePath = projectRoot + "/data/approved_scso.csv";
        boolean needsBudgetReviewColumn = false;

        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] columns;
            while ((columns = csvReader.readNext()) != null) {
                if (columns.length < 13) { 
                    String[] extendedColumns = new String[13]; 
                    System.arraycopy(columns, 0, extendedColumns, 0, columns.length);
                    extendedColumns[12] = "PENDING";
                    requests.add(extendedColumns);
                    needsBudgetReviewColumn = true;
                } else {
                    requests.add(columns);
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            throw new ServletException("Error reading event request data", e);
        }

        if (needsBudgetReviewColumn) {
            try (CSVWriter csvWriter = new CSVWriter(new FileWriter(filePath))) {
                csvWriter.writeAll(requests);
            } catch (IOException e) {
                e.printStackTrace();
                throw new ServletException("Error updating event request data", e);
            }
        }

        request.setAttribute("requests", requests);
        request.getRequestDispatcher("/viewSCSOApprovedRequests.jsp").forward(request, response);
    }
}