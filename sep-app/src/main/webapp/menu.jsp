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

    <!-- Button 1: Visible only to admin -->
    <c:if test="${sessionScope.role == 'hr'}">
        <button>CSO Button</button>
    </c:if>

    <!-- Button 2: Visible to both user and admin -->
    <c:if test="${sessionScope.role == 'cso' || sessionScope.role == 'scso'}">
        <button>SCSO Button</button>
    </c:if>

    <!-- Button 3: Visible to everyone (guest, user, and admin) -->
    <c:if test="${sessionScope.role == 'cso' || sessionScope.role == 'scso' || sessionScope.role == 'hr'}">
        <button>HR Button</button>
    </c:if>

    <!-- Budget Request Button: Visible to project manager and service manager -->
    <c:if test="${sessionScope.role == 'pm' || sessionScope.role == 'sm'}">
        <a href="budgetRequest" class="btn btn-primary mb-2">Create Budget Request</a>
        <a href="staffRecruitmentRequest.jsp">Staff Recruitment Requests</a>
    </c:if>

    <!-- Budget Request Button: Visible to financial manager -->
    <c:if test="${sessionScope.role == 'fm'}">
        <a href="reviewBudgetRequest.jsp">Review Budget Requests</a>
    </c:if>

    <!-- Logout button for all users -->
        <a href="logout" class="btn btn-danger">Logout</a>

</body>
</html>
