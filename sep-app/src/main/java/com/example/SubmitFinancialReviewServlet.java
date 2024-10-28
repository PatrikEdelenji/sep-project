package com.example;

import com.opencsv.CSVWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.FileWriter;
import java.io.IOException;

@WebServlet("/submitBudgetReview")
public class SubmitFinancialReviewServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Retrieve form data
        String clientRecord = request.getParameter("clientRecord");
        String clientName = request.getParameter("clientName");
        String eventType = request.getParameter("eventType");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String expectedAttendees = request.getParameter("expectedAttendees");

        // Check and set values for checkboxes; default to "No" if unchecked
        String decorations = request.getParameter("decorations") != null ? "Yes" : "No";
        String parties = request.getParameter("parties") != null ? "Yes" : "No";
        String photos = request.getParameter("photos") != null ? "Yes" : "No";
        String meals = request.getParameter("meals") != null ? "Yes" : "No";
        String drinks = request.getParameter("drinks") != null ? "Yes" : "No";

        String budget = request.getParameter("budget");
        String budgetReview = request.getParameter("budgetDescription");

        // Path to save the financial review records CSV file
        String projectRoot = System.getProperty("user.dir");
        String filePath = projectRoot + "/data/approved_fm.csv";

        // Write data in CSV format
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(filePath, true))) {
            // Prepare the data as an array of strings
            String[] record = {
                clientRecord,
                clientName,
                eventType,
                fromDate,
                toDate,
                expectedAttendees,
                decorations,
                parties,
                photos,
                meals,
                drinks,
                budget,
                budgetReview
            };

            // Write the data as a new row in the CSV file
            csvWriter.writeNext(record);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServletException("Error saving budget review data", e);
        }

        // Redirect to a confirmation page or back to the main list
        response.sendRedirect("/viewSCSOApprovedRequests");
    }
}
