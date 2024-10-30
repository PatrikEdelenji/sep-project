package com.example;

import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@WebServlet("/fillCommentPage")
public class FillCommentPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("We have reached comment fill");
        String clientRecord = request.getParameter("clientRecord");
        System.out.println("clientRecord of task comment: " + clientRecord);
        String taskDescription = request.getParameter("taskDescription");
        System.out.println("taskDescription of task comment: " + taskDescription);
        String[] recordData = null;

        String projectRoot = System.getProperty("user.dir");
        String filePath = projectRoot + "/data/employee_tasks.csv";

        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] columns;
            while ((columns = csvReader.readNext()) != null) {
                if (columns[0].equals(clientRecord) && columns[2].equals(taskDescription)) { 
                    recordData = columns;
                    break;
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            throw new ServletException("Error reading event data for budget review", e);
        }

        if (recordData != null) {
            request.setAttribute("clientRecord", recordData[0]);
            request.setAttribute("eventType", recordData[1]);
            request.setAttribute("taskDescription", recordData[2]);
            request.setAttribute("assignee", recordData[3]);
            request.setAttribute("priority", recordData[4]);
            request.setAttribute("sender", recordData[5]);
            request.setAttribute("type", recordData[6]);
            request.setAttribute("comments", recordData[7]);
        }

        request.getRequestDispatcher("/commentPage.jsp").forward(request, response);
    }
}