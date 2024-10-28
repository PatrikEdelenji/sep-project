<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu</title>
</head>
<body>
    <h2>Welcome, ${sessionScope.username}!</h2>

    <!-- Button 1: Visible only to Customer Service Officers -->
    <c:if test="${sessionScope.role == 'cso'}">
        <button onclick="window.location.href='/createNewEventRequest'">Create New Event Planning Request</button>
    </c:if>

    <!-- Button 2: Visible only to Senior Customer Service Officers -->
    <c:if test="${sessionScope.role == 'scso'}">
        <button onclick="window.location.href='/viewAllNewRequests'">View New Requests</button>
    </c:if>

    <!-- Button 3: Visible to finanical manager for budget reviews -->
    <c:if test="${sessionScope.role == 'fm'}">
        <button onclick="window.location.href='/viewSCSOApprovedRequests'">View received event requests</button>
    </c:if>
</body>
</html>
