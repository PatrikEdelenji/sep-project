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
            <th>Budget Review</th>
            <th>Actions</th> <!-- New column for the action button -->
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
                <td>${request[12]}</td> <!-- Budget Review -->
                
                <td>
                    <form action="approveBudget" method="post" style="display: inline;">
                        <input type="hidden" name="clientRecord" value="${request[0]}" />
                        <button type="submit">Approve</button>
                    </form>
                    <form action="rejectRequest" method="post" style="display: inline;">
                        <input type="hidden" name="clientRecord" value="${request[0]}" />
                        <button type="submit">Reject</button>
                    </form>
                    <form action="fillBudgetReview" method="get" style="display: inline;">
                        <input type="hidden" name="clientRecord" value="${request[0]}" />
                        <button type="submit">Review Budget</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
