package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewAllNewRequests")
public class ViewAllNewRequestsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<String[]> requests = new ArrayList<>();

        String projectRoot = System.getProperty("user.dir");
        String filePath = projectRoot + "/data/event_requests.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                requests.add(columns);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServletException("Error reading event request data", e);
        }

        request.setAttribute("requests", requests);
        request.getRequestDispatcher("/viewAllNewRequests.jsp").forward(request, response);
    }
}