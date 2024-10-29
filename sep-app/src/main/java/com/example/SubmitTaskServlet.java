package com.example;

import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opencsv.CSVWriter;

@WebServlet("/submitTask")
public class SubmitTaskServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Retrieve form data
        String clientRecord = request.getParameter("clientRecord");
        String eventType = request.getParameter("eventType");
        String description = request.getParameter("description");
        String assignee = request.getParameter("assignee");
        String priority = request.getParameter("priority");
        String department = request.getParameter("department");

        // Get the user's role to identify the sender
        String sender = (String) request.getSession().getAttribute("role");

        // Path to save tasks to CSV
        String projectRoot = System.getProperty("user.dir");
        String filePath = projectRoot + "/data/tasks_csv.csv";

        // Write data to CSV format
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(filePath, true))) {
            // Prepare data for CSV
            String[] record = {
                clientRecord,
                eventType,
                description,
                assignee,
                priority,
                sender,
                department
            };

            // Write to CSV
            csvWriter.writeNext(record);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServletException("Error saving task data", e);
        }

        // Redirect back to a task confirmation page or main page
        response.sendRedirect("/viewClientRecords");
    }
}
