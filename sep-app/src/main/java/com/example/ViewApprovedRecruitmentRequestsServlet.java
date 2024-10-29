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

@WebServlet("/ViewApprovedRecruitmentRequestsServlet")
public class ViewApprovedRecruitmentRequestsServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<String[]> recruitmentRequests = new ArrayList<>();
        
        // Get the real path for the CSV file
        String csvFilePath = "C:/Users/hell0/Ivys Documents/KTH Masters/Modern Methods in Software Engineering/Homeworks/Final Project/sep-project/sep-app/data/staffRecruitmentRequests.csv";
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                
                // Debug print to see what's being read
                System.out.println("Read line: " + line);
                System.out.println("Values length: " + values.length);

                // Need at least 6 values (role, position, department, number, justification, and at least one status)
                if (values.length >= 6) {
                    // Get all statuses from position 6 onwards
                    // List<String> statuses = Arrays.asList(Arrays.copyOfRange(values, 5, values.length));
                    
                    List<String> statuses = new ArrayList<>();
                    for (int i = 5; i < values.length; i++) {
                        if (!values[i].trim().isEmpty()) {
                            statuses.add(values[i].trim());
                        }
                    }
                    
                    // Debug print statuses
                    System.out.println("Statuses found: " + String.join(", ", statuses));
                    

                    // Check if any status is APPROVED or DISAPPROVED
                    boolean hasApprovedOrDisapproved = statuses.stream()
                        .anyMatch(status -> 
                            status.trim().equalsIgnoreCase("APPROVED") || 
                            status.trim().equalsIgnoreCase("DISAPPROVED"));
                    
                    if (hasApprovedOrDisapproved) {
                        String combinedStatus = String.join(", ", statuses);
                        
                        // Debug print
                        System.out.println("Adding request with status: " + combinedStatus);
                        
                    //if (statuses.stream().anyMatch(status -> 
                    //        status.trim().equalsIgnoreCase("APPROVED") || 
                    //        status.trim().equalsIgnoreCase("DISAPPROVED"))) {
                        
                        // Create status string combining all statuses
                        // String combinedStatus = String.join(", ", statuses);
                        
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
            System.out.println("Error reading CSV: " + e.getMessage());
        }

        // Debug print
        System.out.println("Total requests found: " + recruitmentRequests.size());

        request.setAttribute("recruitmentRequests", recruitmentRequests);
        request.getRequestDispatcher("reviewApprovedRecruitmentRequests.jsp")
               .forward(request, response);
    }
}