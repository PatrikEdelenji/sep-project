package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ReviewBudgetRequestServlet")
public class ReviewBudgetRequestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CSV_FILE_PATH = "C:/Users/hell0/Ivys Documents/KTH Masters/Modern Methods in Software Engineering/Homeworks/Final Project/sep-project/sep-app/src/main/resources/budgetRequest.csv"; // Update with the actual file path
    private static final String TEMP_FILE_PATH = "C:/Users/hell0/Ivys Documents/KTH Masters/Modern Methods in Software Engineering/Homeworks/Final Project/sep-project/sep-app/src/main/resources/temp_budgetRequest.csv";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String projectName = request.getParameter("projectName");

        // Temporary file to write updated data
        File inputFile = new File(CSV_FILE_PATH);
        File tempFile = new File(TEMP_FILE_PATH);

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] requestFields = line.split(",");
                if (requestFields[0].equals(projectName)) {
                    // Append the action (approved/disapproved) to the end of the line
                    writer.println(line + "," + action.toUpperCase());
                } else {
                    writer.println(line);
                }
            }
        }

        // Replace original file with the updated file
        if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
            throw new IOException("Could not update the file with the review action.");
        }

        // Redirect back to review page
        response.sendRedirect("reviewBudgetRequest.jsp?reviewSubmitted=true");
    }
}