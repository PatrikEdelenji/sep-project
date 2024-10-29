<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Event Requests</title>
    <style>
        table { width: 80%; margin: 0 auto; border-collapse: collapse; }
        th, td { padding: 8px 12px; border: 1px solid #ddd; text-align: center; }
        th { background-color: #f4f4f4; }
        h2 { text-align: center; }
    </style>
</head>
<body>
    <h2>My Tasks</h2>
    <table>
        <tr>
            <th>Client Record</th>
            <th>Event Type</th>
            <th>Task Description</th>
            <th>Assignee</th>
            <th>Priority</th>
            <th>Sender</th>
            <th>Type</th>
            <th>Comments</th>
            <th>Actions</th> <!-- New column for the action button -->
            
        </tr>
        <c:forEach var="request" items="${requests}">
            <tr>
                <td>${request[0]}</td> <!-- Client Record -->
                <td>${request[1]}</td> <!-- Event Type -->
                <td>${request[2]}</td> <!-- Task Description -->
                <td>${request[3]}</td> <!-- Assignee -->
                <td>${request[4]}</td> <!-- Priority -->
                <td>${request[5]}</td> <!-- Sender -->
                <td>${request[6]}</td> <!-- Type -->
                <td>${request[7]}</td> <!-- Comments to submit plans-->
                
                <td>
                    <form action="viewTask" method="get" style="display: inline;">
                        <input type="hidden" name="clientRecord" value="${request[0]}" />
                        <button type="submit">View task</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
