package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/staffRecruitmentRequest")
public class StaffRecruitmentRequestServlet extends HttpServlet {
    //private static final String CSV_FILE_PATH = "C:/Users/hell0/Ivys Documents/KTH Masters/Modern Methods in Software Engineering/Homeworks/Final Project/sep-project/sep-app/data/staffRecruitmentRequests.csv";

    
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    HttpSession session = request.getSession();
    String role = (String) session.getAttribute("role");

    if ("hr".equals(role)) {
        List<String[]> recruitmentRequests = getRecruitmentRequests();
        request.setAttribute("recruitmentRequests", recruitmentRequests);
        request.getRequestDispatcher("viewRecruitmentRequests.jsp").forward(request, response);
        return;
    }

    if ("sm".equals(role) || "pm".equals(role)) {
        List<String[]> approvedDisapprovedRequests = getApprovedDisapprovedRequests();
        request.setAttribute("recruitmentRequests", approvedDisapprovedRequests);
        request.getRequestDispatcher("reviewApprovedRecruitmentRequests.jsp").forward(request, response);
        return;
    }

    response.sendRedirect("menu.jsp");
}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");


        if (!"sm".equals(role) && !"pm".equals(role)) {
            response.sendRedirect("menu.jsp");
            return;
        }

        String positionTitle = request.getParameter("positionTitle");
        String department = request.getParameter("department");
        String numberOfPositions = request.getParameter("numberOfPositions");
        String justification = request.getParameter("justification");

        String projectRoot = System.getProperty("user.dir");
        String csvFilePath = projectRoot + "/data/staffRecruitmentRequests.csv";

        try (FileWriter writer = new FileWriter(csvFilePath, true);
            PrintWriter printWriter = new PrintWriter(writer)) {
            
            printWriter.printf("%s,%s,%s,%s,%s,Pending%n", role, positionTitle, department, numberOfPositions, justification);
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error saving recruitment request.");
            return;
        }
        response.sendRedirect("menu.jsp?recruitmentRequestSubmitted=true");
    }

    private List<String[]> getRecruitmentRequests() {
        List<String[]> requests = new ArrayList<>();
        
        String projectRoot = System.getProperty("user.dir");
        String csvFilePath = projectRoot + "/data/budgetRequest.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 6) {  
                    requests.add(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return requests;
    }

private List<String[]> getApprovedDisapprovedRequests() {
    List<String[]> requests = new ArrayList<>();
    
    String projectRoot = System.getProperty("user.dir");
    String csvFilePath = projectRoot + "/data/budgetRequest.csv";
    try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length == 6 && ("Approved".equals(data[5]) || "Disapproved".equals(data[5]))) {
                requests.add(data);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return requests;
}

}