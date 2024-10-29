<%@ page import="java.util.*" %>
<%
    List<String[]> recruitmentRequests = (List<String[]>) request.getAttribute("recruitmentRequests");
    if (recruitmentRequests == null) {
        recruitmentRequests = new ArrayList<>();  // Initialize to empty list if null
    }
    
    // Get error message if any
    String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Approved/Disapproved Recruitment Requests</title>
</head>
<body>
    <h2>Approved and Disapproved Recruitment Requests</h2>
    
    <% if (error != null) { %>
        <div style="color: red; margin-bottom: 10px;">
            <%= error %>
        </div>
    <% } %>
    
    <% if (recruitmentRequests.isEmpty()) { %>
        <p>No approved or disapproved recruitment requests found.</p>
    <% } else { %>
        <table border="1">
            <tr>
                <th>Position Title</th>
                <th>Department</th>
                <th>Number of Positions</th>
                <th>Justification</th>
                <th>Status</th>
            </tr>
            <%
                for (String[] recruitrequest : recruitmentRequests) {
            %>
                <tr>
                    <td><%= recruitrequest[0] %></td>
                    <td><%= recruitrequest[1] %></td>
                    <td><%= recruitrequest[2] %></td>
                    <td><%= recruitrequest[3] %></td>
                    <td><%= recruitrequest[4] %></td>
                </tr>
            <%
                }
            %>
        </table>
    <% } %>
</body>
</html>