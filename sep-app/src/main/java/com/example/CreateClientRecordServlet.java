package com.example;

import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@WebServlet("/createClientRecord")
public class CreateClientRecordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String clientRecord = request.getParameter("clientRecord");
        String[] recordData = null;

        String projectRoot = System.getProperty("user.dir");
        String filePath = projectRoot + "/data/approved_admin.csv";

        // Use OpenCSV CSVReader
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] columns;
            while ((columns = csvReader.readNext()) != null) {
                System.out.println("Read line: " + String.join(",", columns));
                if (columns[0].equals(clientRecord)) { // Match client record
                    recordData = columns;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServletException("Error reading client record data", e);
        } catch (CsvValidationException ex) {
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
            request.setAttribute("budgetReview", recordData[12]);

        }

        request.getRequestDispatcher("/clientRecordForm.jsp").forward(request, response);
    }
}