package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/budgetRequest")
public class BudgetRequestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CSV_FILE_PATH = "C:/Users/hell0/Ivys Documents/KTH Masters/Modern Methods in Software Engineering/Homeworks/Final Project/sep-project/sep-app/src/main/resources/budgetRequest.csv"; // Update with the actual file path
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        
        // Only allow SM and PM roles to access
        if (!"sm".equals(role) && !"pm".equals(role)) {
            response.sendRedirect("menu.jsp");
            return;
        }
        
        request.getRequestDispatcher("budgetRequest.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        
        // Only allow SM and PM roles to submit
        if (!"sm".equals(role) && !"pm".equals(role)) {
            response.sendRedirect("menu.jsp");
            return;
        }

        // Get form data
        String projectName = request.getParameter("projectName");
        String currentBudget = request.getParameter("currentBudget");
        String requestedAmount = request.getParameter("requestedAmount");
        String justification = request.getParameter("justification");

        // Here you would typically save this to a database

        // Save data to CSV file
        try (FileWriter fileWriter = new FileWriter(CSV_FILE_PATH, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            // Append data to CSV in the order of form fields
            printWriter.printf("%s,%s,%s,%s%n", projectName, currentBudget, requestedAmount, justification);

            } catch (IOException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error saving budget request.");
                return;
        }

        // For now, we'll just redirect back to menu with a success message
        response.sendRedirect("menu.jsp?budgetRequestSubmitted=true");
    }
}