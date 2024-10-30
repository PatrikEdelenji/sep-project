package com.example;

import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opencsv.CSVWriter;

@WebServlet("/submitNewClientRecord")
public class SubmitNewClientRecordServlet extends HttpServlet {

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
        String budgetReview = request.getParameter("budgetReview");
        String computerNotes = request.getParameter("computerNotes");
        String otherNotes = request.getParameter("otherNotes");

        String decorationsDescription = (request.getParameter("decorationsDescription") == null || request.getParameter("decorationsDescription").equalsIgnoreCase("No")) ? "No" : request.getParameter("decorationsDescription");
        String partiesDescription = (request.getParameter("partiesDescription") == null || request.getParameter("partiesDescription").equalsIgnoreCase("No")) ? "No" : request.getParameter("partiesDescription");
        String photosDescription = (request.getParameter("photosDescription") == null || request.getParameter("photosDescription").equalsIgnoreCase("No")) ? "No" : request.getParameter("photosDescription");
        String mealsDescription = (request.getParameter("mealsDescription") == null || request.getParameter("mealsDescription").equalsIgnoreCase("No")) ? "No" : request.getParameter("mealsDescription");
        String drinksDescription = (request.getParameter("drinksDescription") == null || request.getParameter("drinksDescription").equalsIgnoreCase("No")) ? "No" : request.getParameter("drinksDescription");

        String projectRoot = System.getProperty("user.dir");
        String filePath = projectRoot + "/data/client_record.csv";

        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(filePath, true))) {
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
                computerNotes,
                otherNotes,
                budget,
                budgetReview
            };

            System.out.println("partiesDescription: " + partiesDescription);
            System.out.println("photosDescription: " + photosDescription);
            System.out.println("mealsDescription: " + mealsDescription);
            System.out.println("drinksDescription: " + drinksDescription);

            csvWriter.writeNext(record);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServletException("Error saving expanded event request data", e);
        }

        response.sendRedirect("newClientRecordConfirmationPage.jsp");
    }
}