package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/viewApprovedBudgetRequests")
public class ViewApprovedBudgetRequestsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        // Only allow "pm" and "sm" roles to access
        if (!"pm".equals(role) && !"sm".equals(role)) {
            response.sendRedirect("menu.jsp");
            return;
        }

        List<String[]> budgetRequests = new ArrayList<>();
        
        // Get the real path for the CSV file
        String projectRoot = System.getProperty("user.dir");
        String csvFilePath = projectRoot + "/data/budgetRequest.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length >= 4) {
                    List<String> statuses = Arrays.asList(Arrays.copyOfRange(values, 4, values.length));

                    // Check if any status in the row is "APPROVED" or "DISAPPROVED"
                    if (statuses.stream().anyMatch(status ->
                            status.trim().equalsIgnoreCase("APPROVED") ||
                            status.trim().equalsIgnoreCase("DISAPPROVED"))) {
                        
                        String combinedStatus = String.join(", ", statuses);

                        budgetRequests.add(new String[]{
                            values[0],  // project name
                            values[1],  // current budget
                            values[2],  // requested amount
                            values[3],  // justification
                            combinedStatus  // combined status string
                        });
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error reading CSV file: " + e.getMessage());
        }

        request.setAttribute("budgetRequests", budgetRequests);
        request.getRequestDispatcher("reviewApprovedBudgetRequests.jsp")
               .forward(request, response);
    }
}
