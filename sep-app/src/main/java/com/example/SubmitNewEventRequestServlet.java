package com.example;

import java.io.FileWriter;
import java.io.IOException;

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
        String clientRecord = request.getParameter("clientRecord"); // Generated or manually entered
        String clientName = request.getParameter("clientName");
        String eventType = request.getParameter("eventType");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String expectedAttendees = request.getParameter("expectedAttendees");
        String[] preferencesArray = request.getParameterValues("preferences");
        String budget = request.getParameter("budget");

        // Join preferences array into a single comma-separated string
        String preferences = preferencesArray != null ? String.join(", ", preferencesArray) : "";

        // Specify the path to the CSV file
        String projectRoot = System.getProperty("user.dir");
        String filePath = projectRoot + "/data/event_requests.csv";

        // Append data in CSV format
        try (FileWriter csvWriter = new FileWriter(filePath, true)) {
            csvWriter.append(clientRecord).append(",")
                     .append(clientName).append(",")
                     .append(eventType).append(",")
                     .append(fromDate).append(",")
                     .append(toDate).append(",")
                     .append(expectedAttendees).append(",")
                     .append(preferences).append(",")
                     .append(budget).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServletException("Error saving event request data", e);
        }

        // Redirect to a confirmation page
        response.sendRedirect("newEventConfirmationPage.jsp");
    }
}