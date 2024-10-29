package com.example;

import com.opencsv.CSVReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import com.opencsv.exceptions.CsvValidationException;

@WebServlet("/fillBudgetReview")
public class FillBudgetReviewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("We have reached budget review fill");
        String clientRecord = request.getParameter("clientRecord");
        String[] recordData = null;

        String projectRoot = System.getProperty("user.dir");
        String filePath = projectRoot + "/data/approved_scso.csv";

        // Use OpenCSV CSVReader
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] columns;
            while ((columns = csvReader.readNext()) != null) {
                if (columns[0].equals(clientRecord)) { // Match client record
                    recordData = columns;
                    break;
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            throw new ServletException("Error reading event data for budget review", e);
        }

        if (recordData != null) {
            // Populate the fields based on CSV data
            request.setAttribute("clientRecord", recordData[0]);
            request.setAttribute("clientName", recordData[1]);
            request.setAttribute("eventType", recordData[2]);
            request.setAttribute("fromDate", recordData[3]);
            request.setAttribute("toDate", recordData[4]);
            request.setAttribute("expectedAttendees", recordData[5]);
            request.setAttribute("decorations", recordData[6]);
            request.setAttribute("parties", recordData[7]);
            request.setAttribute("photos", recordData[8]);
            request.setAttribute("meals", recordData[9]);
            request.setAttribute("drinks", recordData[10]);
            request.setAttribute("budget", recordData[11]);
            request.setAttribute("budgetReview", recordData[12]);
        }

        // Forward to the budget review JSP page
        request.getRequestDispatcher("/budgetReviewPage.jsp").forward(request, response);
    }
}