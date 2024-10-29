<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Client Records</title>
    <style>
        table { width: 80%; margin: 0 auto; border-collapse: collapse; }
        th, td { padding: 8px 12px; border: 1px solid #ddd; text-align: center; }
        th { background-color: #f4f4f4; }
        h2 { text-align: center; }
    </style>
</head>
<body>
    <h2>All Event Requests</h2>
    <table>
        <tr>
            <th>Client Record</th>
            <th>Client Name</th>
            <th>Event Type</th>
            <th>Event Description</th>
            <th>From Date</th>
            <th>To Date</th>
            <th>Expected Attendees</th>
            <th>Decorations</th>
            <th>Parties</th>
            <th>Photos/Filming</th>
            <th>Meals</th>
            <th>Drinks</th>
            <th>IT issues</th>
            <th>Other notes</th>
            <th>Budget</th>
            <th>Budget Review</th>
            <c:if test="${sessionScope.role == 'pm' || sessionScope.role == 'services'}">
            <th>Tasks</th>
            </c:if>
        </tr>
        <c:forEach var="request" items="${requests}">
            <tr>
                <td>${request[0]}</td> <!-- Client Record -->
                <td>${request[1]}</td> <!-- Client Name -->
                <td>${request[2]}</td> <!-- Event Type -->
                <td>${request[3]}</td> <!-- Event Description -->
                <td>${request[4]}</td> <!-- From Date -->
                <td>${request[5]}</td> <!-- To Date -->
                <td>${request[6]}</td> <!-- Expected Attendees -->
                <td>${request[7]}</td> <!-- Decorations (Yes/No) -->
                <td>${request[8]}</td> <!-- Parties (Yes/No) -->
                <td>${request[9]}</td> <!-- Photos/Filming (Yes/No) -->
                <td>${request[10]}</td> <!-- Meals (Yes/No) -->
                <td>${request[11]}</td> <!-- Drinks (Yes/No) -->
                <td>${request[12]}</td> <!-- IT issues -->
                <td>${request[13]}</td> <!-- Other notes -->
                <td>${request[14]}</td> <!-- Budget -->
                <td>${request[15]}</td> <!-- Budget Review -->
                <c:if test="${sessionScope.role == 'pm' || sessionScope.role == 'services'}">
                <td>
                <form action="createTask.jsp" method="get">
                    <input type="hidden" name="clientRecord" value="${request[0]}" />
                    <input type="hidden" name="eventType" value="${request[2]}" />
                    <button type="submit">Create Tasks</button>
                </form>
                </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
