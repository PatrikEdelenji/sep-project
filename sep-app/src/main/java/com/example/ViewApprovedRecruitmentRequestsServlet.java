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

@WebServlet("/viewApprovedRecruitmentRequests")
public class ViewApprovedRecruitmentRequestsServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<String[]> recruitmentRequests = new ArrayList<>();
        
        // Get the real path for the CSV file
        //String csvFilePath = "C:/Users/hell0/Ivys Documents/KTH Masters/Modern Methods in Software Engineering/Homeworks/Final Project/sep-project/sep-app/data/staffRecruitmentRequests.csv";
        String projectRoot = System.getProperty("user.dir");
        String csvFilePath = projectRoot + "/data/staffRecruitmentRequests.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length >= 6) {
                    List<String> statuses = Arrays.asList(Arrays.copyOfRange(values, 5, values.length));

                    if (statuses.stream().anyMatch(status -> 
                            status.trim().equalsIgnoreCase("APPROVED") || 
                            status.trim().equalsIgnoreCase("DISAPPROVED"))) {

                        String combinedStatus = String.join(", ", statuses);
                        
                        recruitmentRequests.add(new String[]{
                            values[1],  // position title
                            values[2],  // department
                            values[3],  // number of positions
                            values[4],  // justification
                            combinedStatus  // combined status string
                        });
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error reading CSV file: " + e.getMessage());
        }
        
        request.setAttribute("recruitmentRequests", recruitmentRequests);
        request.getRequestDispatcher("reviewapprovedrecruitmentrequests.jsp")
               .forward(request, response);
    }
}