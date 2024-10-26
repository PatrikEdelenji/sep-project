package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/budgetRequest")
public class BudgetRequestServlet extends HttpServlet {
    // Store budget requests in memory (in a real application, this would be in a database)
    private static List<BudgetRequest> budgetRequests = new ArrayList<>();

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
        String username = (String) session.getAttribute("username");

        // Only allow SM and PM roles to submit
        if (!"sm".equals(role) && !"pm".equals(role)) {
            response.sendRedirect("menu.jsp");
            return;
        }

        // Get form parameters
        String projectName = request.getParameter("projectName");
        double currentBudget = Double.parseDouble(request.getParameter("currentBudget"));
        double requestedAmount = Double.parseDouble(request.getParameter("requestedAmount"));
        String justification = request.getParameter("justification");

        // Create new budget request
        BudgetRequest budgetRequest = new BudgetRequest(
            UUID.randomUUID().toString(),
            username,
            projectName,
            currentBudget,
            requestedAmount,
            justification,
            "Pending"
        );

        budgetRequests.add(budgetRequest);

        // Redirect back to menu with success message
        response.sendRedirect("menu.jsp?budgetRequestSubmitted=true");
    }
}

// BudgetRequest.java
class BudgetRequest {
    private String id;
    private String requester;
    private String projectName;
    private double currentBudget;
    private double requestedAmount;
    private String justification;
    private String status;

    public BudgetRequest(String id, String requester, String projectName, 
                        double currentBudget, double requestedAmount, 
                        String justification, String status) {
        this.id = id;
        this.requester = requester;
        this.projectName = projectName;
        this.currentBudget = currentBudget;
        this.requestedAmount = requestedAmount;
        this.justification = justification;
        this.status = status;
    }

    // Getters
    public String getId() { return id; }
    public String getRequester() { return requester; }
    public String getProjectName() { return projectName; }
    public double getCurrentBudget() { return currentBudget; }
    public double getRequestedAmount() { return requestedAmount; }
    public String getJustification() { return justification; }
    public String getStatus() { return status; }
}