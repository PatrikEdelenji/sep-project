package com.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@WebServlet("/viewMyTasks")
public class ViewMyTasksServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        if (username == null || role == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if session is missing data
            return;
        }

        List<String[]> allTasks = new ArrayList<>();
        List<String[]> userTasks = new ArrayList<>();

        String projectRoot = System.getProperty("user.dir");
        String filePath = projectRoot + "/data/employee_tasks.csv";

        // Read all tasks from CSV
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] columns;
            while ((columns = csvReader.readNext()) != null) {
                allTasks.add(columns);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServletException("Error reading task data", e);
        } catch (CsvValidationException ex) {
            ex.printStackTrace();
        }

        // Filter tasks based on role and username
        for (String[] task : allTasks) {
            String taskAssignee = task[3];
            String taskDepartment = task[6];

            // Include task if assigned to the user or relevant to the user’s department
            if (taskAssignee.equals(username) || taskDepartment.equals("General") || taskDepartment.equals(role)) {
                userTasks.add(task);
            }
        }

        request.setAttribute("requests", userTasks); // Set filtered tasks for frontend
        request.getRequestDispatcher("/viewMyTasks.jsp").forward(request, response);
    }
}
