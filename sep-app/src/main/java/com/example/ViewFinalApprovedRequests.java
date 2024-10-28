package com.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/viewFinalApprovedRequests")
public class ViewFinalApprovedRequests extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<String[]> requests = new ArrayList<>();

        String projectRoot = System.getProperty("user.dir");
        String filePath = projectRoot + "/data/approved_admin.csv";

        // Using OpenCSV CSVReader
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] columns;
            while ((columns = csvReader.readNext()) != null) {
                requests.add(columns);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServletException("Error reading event request data", e);
        } catch (CsvValidationException ex) {
        }

        request.setAttribute("requests", requests);
        request.getRequestDispatcher("/viewFinalApprovedRequests.jsp").forward(request, response);
    }
}