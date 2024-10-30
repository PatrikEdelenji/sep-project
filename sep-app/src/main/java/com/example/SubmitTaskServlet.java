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
        
        String clientRecord = request.getParameter("clientRecord");
        String eventType = request.getParameter("eventType");
        String description = request.getParameter("description");
        String assignee = request.getParameter("assignee");
        String priority = request.getParameter("priority");
        String department = request.getParameter("department");


        String sender = (String) request.getSession().getAttribute("role");

        String projectRoot = System.getProperty("user.dir");
        String filePath = projectRoot + "/data/employee_tasks.csv";

        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(filePath, true))) {
            String[] record = {
                clientRecord,
                eventType,
                description,
                assignee,
                priority,
                sender,
                department,
                "-"
            };

            csvWriter.writeNext(record);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServletException("Error saving task data", e);
        }

        response.sendRedirect("/viewClientRecords");
    }
}
