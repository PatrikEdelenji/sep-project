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

@WebServlet("/staffRecruitmentRequest")
public class StaffRecruitmentRequestServlet extends HttpServlet {
    private static final String CSV_FILE_PATH = "C:/Users/hell0/Ivys Documents/KTH Masters/Modern Methods in Software Engineering/Homeworks/Final Project/sep-project/sep-app/data/staffRecruitmentRequests.csv"; // Update with the actual path

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
        
        request.getRequestDispatcher("staffRecruitmentRequest.jsp").forward(request, response);
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
        String positionTitle = request.getParameter("positionTitle");
        String department = request.getParameter("department");
        String numberOfPositions = request.getParameter("numberOfPositions");
        String justification = request.getParameter("justification");

        // Save data to CSV
        try (FileWriter writer = new FileWriter(CSV_FILE_PATH, true);
             PrintWriter printWriter = new PrintWriter(writer)) {
            printWriter.printf("%s,%s,%s,%s,%s%n", role, positionTitle, department, numberOfPositions, justification);
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error saving recruitment request.");
            return;
        }

        response.sendRedirect("menu.jsp?recruitmentRequestSubmitted=true");
    }
}