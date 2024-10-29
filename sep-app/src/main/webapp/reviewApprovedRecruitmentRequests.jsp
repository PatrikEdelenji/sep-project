<%@ page import="java.util.*" %>
<%
    List<String[]> recruitmentRequests = (List<String[]>) request.getAttribute("recruitmentRequests");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Approved/Disapproved Recruitment Requests</title>
</head>
<body>
    <h2>Approved and Disapproved Recruitment Requests</h2>
    <table border="1">
        <tr>
            <th>Position Title</th>
            <th>Department</th>
            <th>Number of Positions</th>
            <th>Justification</th>
            <th>Status</th>
        </tr>
        <%
            for (String[] request : recruitmentRequests) {
        %>
            <tr>
                <td><%= request[1] %></td>
                <td><%= request[2] %></td>
                <td><%= request[3] %></td>
                <td><%= request[4] %></td>
                <td><%= request[5] %></td>
            </tr>
        <%
            }
        %>
    </table>
</body>
</html>