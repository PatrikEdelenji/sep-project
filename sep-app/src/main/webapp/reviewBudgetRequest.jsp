<%@ page import="java.io.*, java.util.*" %>
<%
    String csvFilePath = "C:/Users/hell0/Ivys Documents/KTH Masters/Modern Methods in Software Engineering/Homeworks/Final Project/sep-project/sep-app/src/main/resources/budgetRequest.csv"; // Update with the actual file path
    List<String[]> requests = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            requests.add(line.split(","));
        }
    } catch (IOException e) {
        e.printStackTrace();
        out.println("<p>Error loading budget requests.</p>");
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Review Budget Requests</title>
</head>
<body>
    <h2>Review Budget Adjustment Requests</h2>
    <table border="1">
        <tr>
            <th>Project Name</th>
            <th>Current Budget</th>
            <th>Requested Amount</th>
            <th>Justification</th>
            <th>Action</th>
        </tr>
        <%
            for (String[] budgetRequest : requests) {
        %>
            <tr>
                <td><%= budgetRequest[0] %></td>
                <td><%= budgetRequest[1] %></td>
                <td><%= budgetRequest[2] %></td>
                <td><%= budgetRequest[3] %></td>
                <td><%= budgetRequest.length > 5 ? budgetRequest[5] : "N/A" %></td> <!-- Display approved budget if available -->
                <td>
                    <form action="ReviewBudgetRequestServlet" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="approve">
                        <input type="hidden" name="projectName" value="<%= budgetRequest[0] %>">
                        <label>Final Budget:</label>
                        <input type="number" name="finalBudget" step="0.01" required>
                        <button type="submit">Approve</button>
                    </form>
                    <form action="ReviewBudgetRequestServlet" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="disapprove">
                        <input type="hidden" name="projectName" value="<%= budgetRequest[0] %>">
                        <button type="submit">Disapprove</button>
                    </form>
                </td>
            </tr>
        <%
            }
        %>
    </table>
</body>
</html>