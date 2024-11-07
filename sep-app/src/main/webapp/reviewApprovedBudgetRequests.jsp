<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Approved/Disapproved Budget Requests</title>
</head>
<body>
    <h2>Approved and Disapproved Budget Requests</h2>
    <table border="1">
        <tr>
            <th>Project Name</th>
            <th>Current Budget ($)</th>
            <th>Requested Amount ($)</th>
            <th>Justification</th>
            <th>Status</th>
        </tr>
        <c:forEach var="budgetRequest" items="${budgetRequests}">
            <tr>
                <td>${budgetRequest[0]}</td>
                <td>${budgetRequest[1]}</td>
                <td>${budgetRequest[2]}</td>
                <td>${budgetRequest[3]}</td>
                <td>${budgetRequest[4]}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
