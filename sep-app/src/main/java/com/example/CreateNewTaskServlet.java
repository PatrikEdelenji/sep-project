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

@WebServlet("/createNewTask")
public class CreateNewTaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String clientRecord = request.getParameter("clientRecord");
        String[] recordData = null;

        String projectRoot = System.getProperty("user.dir");
        String filePath = projectRoot + "/data/client_record.csv";


        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] columns;
            while ((columns = csvReader.readNext()) != null) {
                System.out.println("Read line: " + String.join(",", columns));
                if (columns[0].equals(clientRecord)) {
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
            request.setAttribute("eventDescription", recordData[3]);
            request.setAttribute("fromDate", recordData[4]);
            request.setAttribute("toDate", recordData[5]);
            request.setAttribute("expectedAttendees", recordData[6]);
            request.setAttribute("decorations", recordData[7]);
            request.setAttribute("parties", recordData[8]);
            request.setAttribute("photos", recordData[9]);
            request.setAttribute("meals", recordData[10]);
            request.setAttribute("drinks", recordData[11]);
            request.setAttribute("ITdep", recordData[12]);
            request.setAttribute("otherNote", recordData[13]);
            request.setAttribute("budget", recordData[14]);
            request.setAttribute("budgetReview", recordData[15]);

            System.out.println("clientRecord parameter: " + clientRecord);
            System.out.println("File path: " + filePath);
            System.out.println("Photos: " + recordData[9]);




        }

        System.out.println("clientRecord parameter: " + clientRecord);
        System.out.println("File path: " + filePath);
        System.out.println("Photos: " + recordData[9]);

        request.getRequestDispatcher("/createTask.jsp").forward(request, response);
    }
}