package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/submitNewEventRequest")
public class SubmitNewEventRequestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Retrieve form data
        String clientRecord;
        String isNewClient = request.getParameter("isNewClient");

        // Generate clientRecord if new client, otherwise get the input value
        if ("yes".equals(isNewClient)) {
            clientRecord = generateRandomClientRecord();
        } else {
            clientRecord = request.getParameter("clientRecord");
        }

        String clientName = request.getParameter("clientName");
        String eventType = request.getParameter("eventType");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String expectedAttendees = request.getParameter("expectedAttendees");
        String budget = request.getParameter("budget");

        // Retrieve checkbox values with defaulting to "No" if they aren't checked
        String decorations = request.getParameter("decorations") != null ? "Yes" : "No";
        String parties = request.getParameter("parties") != null ? "Yes" : "No";
        String photos = request.getParameter("photos") != null ? "Yes" : "No";
        String meals = request.getParameter("meals") != null ? "Yes" : "No";
        String drinks = request.getParameter("drinks") != null ? "Yes" : "No";

        // Path to save CSV file
        String projectRoot = System.getProperty("user.dir");
        String filePath = projectRoot + "/data/event_requests.csv";

        // Write data in CSV format
        try (FileWriter csvWriter = new FileWriter(filePath, true)) {
            csvWriter.append(clientRecord).append(",")
                     .append(clientName).append(",")
                     .append(eventType).append(",")
                     .append(fromDate).append(",")
                     .append(toDate).append(",")
                     .append(expectedAttendees).append(",")
                     .append(decorations).append(",")
                     .append(parties).append(",")
                     .append(photos).append(",")
                     .append(meals).append(",")
                     .append(drinks).append(",")
                     .append(budget).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServletException("Error saving event request data", e);
        }

        // Redirect to confirmation page
        response.sendRedirect("newEventConfirmationPage.jsp");
    }

    // Helper method to generate a 5-character alphanumeric client record
    private String generateRandomClientRecord() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(5);
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}