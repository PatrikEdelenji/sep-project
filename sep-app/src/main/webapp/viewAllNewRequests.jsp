<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All New Event Requests</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f9f9f9; }
        table { width: 90%; margin: 20px auto; border-collapse: collapse; }
        th, td { padding: 10px; border: 1px solid #ddd; text-align: center; }
        th { background-color: #333; color: #fff; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        h2 { text-align: center; color: #333; margin-top: 20px; }
    </style>
</head>
<body>
    <h2>All Event Requests</h2>
    <table>
        <tr>
            <th>Client Record</th>
            <th>Client Name</th>
            <th>Event Type</th>
            <th>From Date</th>
            <th>To Date</th>
            <th>Expected Attendees</th>
            <th>Decorations</th>
            <th>Parties</th>
            <th>Photos/Filming</th>
            <th>Meals</th>
            <th>Drinks</th>
            <th>Budget</th>
        </tr>
        <c:forEach var="request" items="${requests}">
            <tr>
                <td>${request[0]}</td> <!-- Client Record -->
                <td>${request[1]}</td> <!-- Client Name -->
                <td>${request[2]}</td> <!-- Event Type -->
                <td>${request[3]}</td> <!-- From Date -->
                <td>${request[4]}</td> <!-- To Date -->
                <td>${request[5]}</td> <!-- Expected Attendees -->
                <td>${request[6]}</td> <!-- Decorations (Yes/No) -->
                <td>${request[7]}</td> <!-- Parties (Yes/No) -->
                <td>${request[8]}</td> <!-- Photos/Filming (Yes/No) -->
                <td>${request[9]}</td> <!-- Meals (Yes/No) -->
                <td>${request[10]}</td> <!-- Drinks (Yes/No) -->
                <td>${request[11]}</td> <!-- Budget -->
            </tr>
        </c:forEach>
    </table>
</body>
</html>
