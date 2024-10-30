package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opencsv.CSVWriter;  // Ensure this import for opencsv

@WebServlet("/submitNewEventRequest")
public class SubmitNewEventRequestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String clientRecord;
        String isNewClient = request.getParameter("isNewClient");

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

        String decorations = request.getParameter("decorations") != null ? "Yes" : "No";
        String parties = request.getParameter("parties") != null ? "Yes" : "No";
        String photos = request.getParameter("photos") != null ? "Yes" : "No";
        String meals = request.getParameter("meals") != null ? "Yes" : "No";
        String drinks = request.getParameter("drinks") != null ? "Yes" : "No";

        String projectRoot = System.getProperty("user.dir");
        String filePath = projectRoot + "/data/event_requests.csv";

        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(filePath, true))) {
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
                budget
            };
            csvWriter.writeNext(record);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServletException("Error saving event request data", e);
        }

        response.sendRedirect("newEventConfirmationPage.jsp");
    }

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