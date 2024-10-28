package com.example;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/submitNewClientRecord")
public class SubmitNewClientRecordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Retrieve form data
        String clientRecord = request.getParameter("clientRecord");
        String clientName = request.getParameter("clientName");
        String eventType = request.getParameter("eventType");
        String eventDescription = request.getParameter("eventDescription");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String expectedAttendees = request.getParameter("expectedAttendees");
        String budget = request.getParameter("budget");

        // Retrieve optional description fields, defaulting to an empty string if they aren't provided
        String decorationsDescription = (request.getParameter("decorationsDescription") != null) ? request.getParameter("decorationsDescription") : "";
        String partiesDescription = (request.getParameter("partiesDescription") != null) ? request.getParameter("partiesDescription") : "";
        String photosDescription = (request.getParameter("photosDescription") != null) ? request.getParameter("photosDescription") : "";
        String mealsDescription = (request.getParameter("mealsDescription") != null) ? request.getParameter("mealsDescription") : "";
        String drinksDescription = (request.getParameter("drinksDescription") != null) ? request.getParameter("drinksDescription") : "";

        // Path to save the expanded client records CSV file
        String projectRoot = System.getProperty("user.dir");
        String filePath = projectRoot + "/data/client_records.csv";

        // Write data in CSV format
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(filePath, true))) {
            // Prepare the data as an array of strings
            String[] record = {
                clientRecord,
                clientName,
                eventType,
                eventDescription,
                fromDate,
                toDate,
                expectedAttendees,
                decorationsDescription,
                partiesDescription,
                photosDescription,
                mealsDescription,
                drinksDescription,
                budget
            };

            // Write the data as a new row in the CSV file
            csvWriter.writeNext(record);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServletException("Error saving expanded event request data", e);
        }

        // Redirect to a confirmation page or back to the main list
        response.sendRedirect("newClientRecordConfirmationPage.jsp");
    }
}