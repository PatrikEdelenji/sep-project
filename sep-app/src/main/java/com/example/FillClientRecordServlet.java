package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/fillClientRecord")
public class FillClientRecordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String clientRecord = request.getParameter("clientRecord");
        String[] recordData = null;

        String projectRoot = System.getProperty("user.dir");
        String filePath = projectRoot + "/data/event_requests.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                System.out.println("Read line: " + line);
                if (columns[0].equals(clientRecord)) { // Match client record
                    recordData = columns;
                    break;
                }
            }
        }

        if (recordData != null) {
            // Pass the record data to the JSP page
            request.setAttribute("clientRecord", recordData[0]);
            request.setAttribute("clientName", recordData[1]);
            request.setAttribute("eventType", recordData[2]);
            request.setAttribute("fromDate", recordData[3]);
            request.setAttribute("toDate", recordData[4]);
            request.setAttribute("expectedAttendees", recordData[5]);
            request.setAttribute("decorations", recordData[6]);
            System.out.println("decorations: " + recordData[6]);
            request.setAttribute("parties", recordData[7]);
            System.out.println("parties: " + recordData[7]);
            request.setAttribute("photos", recordData[8]);
            System.out.println("photos: " + recordData[8]);
            request.setAttribute("meals", recordData[9]);
            System.out.println("meals: " + recordData[9]);
            request.setAttribute("drinks", recordData[10]);
            System.out.println("drinks: " + recordData[10]);
            request.setAttribute("budget", recordData[11]);
        }

        request.getRequestDispatcher("/clientRecordForm.jsp").forward(request, response);
    }
}