package com.example;

import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/createClientRecord")
public class CreateClientRecordServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String clientRecord = request.getParameter("clientRecord");
        String clientName = request.getParameter("clientName");
        String eventType = request.getParameter("eventType");
        String eventDescription = request.getParameter("eventDescription");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String expectedAttendees = request.getParameter("expectedAttendees");
        String budget = request.getParameter("budget");

        // Descriptions for preferences
        String decorationsDescription = request.getParameter("decorationsDescription");
        String partiesDescription = request.getParameter("partiesDescription");
        String photosDescription = request.getParameter("photosDescription");
        String mealsDescription = request.getParameter("mealsDescription");
        String drinksDescription = request.getParameter("drinksDescription");

        String projectRoot = System.getProperty("user.dir");
        String filePath = projectRoot + "/data/client_record.csv";

        try (FileWriter csvWriter = new FileWriter(filePath, true)) {
            csvWriter.append(clientRecord).append(",")
                     .append(clientName).append(",")
                     .append(eventType).append(",")
                     .append(eventDescription).append(",")
                     .append(fromDate).append(",")
                     .append(toDate).append(",")
                     .append(expectedAttendees).append(",")
                     .append(decorationsDescription != null ? decorationsDescription : "").append(",")
                     .append(partiesDescription != null ? partiesDescription : "").append(",")
                     .append(photosDescription != null ? photosDescription : "").append(",")
                     .append(mealsDescription != null ? mealsDescription : "").append(",")
                     .append(drinksDescription != null ? drinksDescription : "").append(",")
                     .append(budget).append("\n");
        } catch (IOException e) {
            throw new ServletException("Error saving expanded event request data", e);
        }

        response.sendRedirect("viewAllNewRequests.jsp");
    }
}