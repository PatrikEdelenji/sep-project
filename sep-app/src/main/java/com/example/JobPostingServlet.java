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

@WebServlet("/jobPosting")
public class JobPostingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CSV_FILE_PATH = "C:/Users/hell0/Ivys Documents/KTH Masters/Modern Methods in Software Engineering/Homeworks/Final Project/sep-project/sep-app/data/jobPostings.csv";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        // Only allow HR role to access
        if (!"hr".equals(role)) {
            response.sendRedirect("menu.jsp");
            return;
        }

        request.getRequestDispatcher("jobPosting.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        // Only allow HR role to submit
        if (!"hr".equals(role)) {
            response.sendRedirect("menu.jsp");
            return;
        }

        // Get form data
        String jobTitle = request.getParameter("jobTitle");
        String department = request.getParameter("department");
        String jobDescription = request.getParameter("jobDescription");
        String qualifications = request.getParameter("qualifications");
        String salaryRange = request.getParameter("salaryRange");

        // Save data to CSV file
        try (FileWriter fileWriter = new FileWriter(CSV_FILE_PATH, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            printWriter.printf("%s,%s,%s,%s,%s%n", jobTitle, department, jobDescription, qualifications, salaryRange);

        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error saving job posting.");
            return;
        }

        response.sendRedirect("menu.jsp?jobPostingSubmitted=true");
    }
}