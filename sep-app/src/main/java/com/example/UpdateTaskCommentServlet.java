package com.example;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

@WebServlet("/updateTaskComment")
public class UpdateTaskCommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieve form data
        String clientRecord = request.getParameter("clientRecord");
        String taskDescription = request.getParameter("taskDescription");

        String budgetReview = request.getParameter("comments");

        // Define file path
        String projectRoot = System.getProperty("user.dir");
        String filePath = projectRoot + "/data/employee_tasks.csv";

        // Read all records and update the matching record
        List<String[]> records = new ArrayList<>();
        boolean recordUpdated = false;

        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] record;
            while ((record = csvReader.readNext()) != null) {
                if (record[0].equals(clientRecord) && record[2].equals(taskDescription)) { // Locate the record by clientRecord
                    record[7] = budgetReview;
                    recordUpdated = true;
                }
                records.add(record); // Add the record to the list
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServletException("Error reading financial review data", e);
        } catch (CsvValidationException ex) {
        }

        // Write all records back to the CSV file with the updated record
        if (recordUpdated) {
            try (CSVWriter csvWriter = new CSVWriter(new FileWriter(filePath))) {
                csvWriter.writeAll(records); // Write all records, including the updated one
            } catch (IOException e) {
                e.printStackTrace();
                throw new ServletException("Error updating financial review data", e);
            }
        }

        // Redirect to a confirmation page or back to the main list
        response.sendRedirect("/viewMyTasks");
    }
}
