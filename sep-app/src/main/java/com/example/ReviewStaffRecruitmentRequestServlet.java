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

@WebServlet("/ReviewStaffRecruitmentRequestServlet")
public class ReviewStaffRecruitmentRequestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CSV_FILE_PATH = "C:/Users/hell0/Ivys Documents/KTH Masters/Modern Methods in Software Engineering/Homeworks/Final Project/sep-project/sep-app/data/staffRecruitmentRequests.csv"; // Update with the actual path
    private static final String TEMP_FILE_PATH = "C:/Users/hell0/Ivys Documents/KTH Masters/Modern Methods in Software Engineering/Homeworks/Final Project/sep-project/sep-app/data/temp_staffRecruitmentRequests.csv";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String positionTitle = request.getParameter("positionTitle");
        String finalNrofStaff = request.getParameter("finalNrofStaff");
        

        String projectRoot = System.getProperty("user.dir");
        String csvFilePath = projectRoot + "/data/staffRecruitmentRequests.csv";
        String tempFilePath = projectRoot + "/data/temp_staffRecruitmentRequests.csv";

        File inputFile = new File(csvFilePath);
        File tempFile = new File(tempFilePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] requestFields = line.split(",");
                
                if (requestFields[1].equals(positionTitle)) {
                    if ("approve".equals(action)) {
                        writer.println(String.join(",", requestFields) + "," + "APPROVED" + "," + finalNrofStaff);
                    } else {
                        writer.println(String.join(",", requestFields) + "," + "DISAPPROVED");
                    }
                } else {
                    writer.println(line);
                }
            }
        }

        if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
            throw new IOException("Could not update the file with the review action.");
        }

        response.sendRedirect("reviewStaffRecruitmentRequest.jsp?reviewSubmitted=true");
    }
}