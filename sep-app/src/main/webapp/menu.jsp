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

    <!-- BVisible only to Customer Service Officers -->
    <c:if test="${sessionScope.role == 'cso'}">
        <button onclick="window.location.href='/createNewEventRequest'">Create New Event Planning Request</button>
        
    </c:if>

    <!-- Visible only to Senior Customer Service Officers -->
    <c:if test="${sessionScope.role == 'scso'}">
        <button onclick="window.location.href='/viewAllNewRequests'">View New Requests</button>
        <button onclick="window.location.href='/viewFinalApprovedRequests'">View admin approved requests</button>
    </c:if>

    <!-- Visible to finanical manager for budget reviews -->
    <c:if test="${sessionScope.role == 'fm'}">
        <button onclick="window.location.href='/viewSCSOApprovedRequests'">View received event requests</button>
    </c:if>

        <!-- Visible to finanical manager for budget reviews -->
    <c:if test="${sessionScope.role == 'admin'}">
        <button onclick="window.location.href='/viewFMApprovedRequests'">View FM approve requests</button>
    </c:if>

          <!-- Visible to  -->
    <c:if test="${sessionScope.role == 'fm' || sessionScope.role == 'scso' || sessionScope.role == 'admin' || sessionScope.role == 'sm' || sessionScope.role == 'pm'}">
        <button onclick="window.location.href='/viewClientRecords'">View client records</button>
        
    </c:if>

    <!-- Budget Request Button: Visible to project manager and service manager -->
    <c:if test="${sessionScope.role == 'pm' || sessionScope.role == 'sm'}">
        <a href="budgetRequest" class="btn btn-primary mb-2">Create Budget Request</a>
        <a href="staffRecruitmentRequest.jsp">Staff Recruitment Requests</a>
        <a href="viewApprovedRecruitmentRequests" class="btn btn-primary">Review Approved/Disapproved Requests</a>
    </c:if>

    <!-- Budget Request Button: Visible to financial manager -->
    <c:if test="${sessionScope.role == 'fm'}">
        <a href="reviewBudgetRequest.jsp">Review Budget Requests</a>
    </c:if>

    <!-- Recruitment Request Review Button: Visible to HR -->
    <c:if test="${sessionScope.role == 'hr'}">
        <a href="reviewStaffRecruitmentRequest.jsp">Review Staff Recruitment Requests</a>
    </c:if>

    <!-- Budget Request Button: Visible to project manager and service manager -->
    <c:if test="${sessionScope.role == 'pm' || sessionScope.role == 'sm'}">
        <a href="/viewMyTasks">View assigned tasks</a>
        <a href="budgetRequest" class="btn btn-primary mb-2">Create Budget Request</a>
        <a href="staffRecruitmentRequest.jsp">Staff Recruitment Requests</a>
        <a href="viewApprovedRecruitmentRequests" class="btn btn-primary">Review Approved/Disapproved Requests</a>
    </c:if>

    <!-- Budget Request Button: Visible to financial manager -->
    <c:if test="${sessionScope.role == 'fm'}">
        <a href="reviewBudgetRequest.jsp">Review Budget Requests</a>
    </c:if>

    <!-- Recruitment Request Review Button: Visible to HR -->
    <c:if test="${sessionScope.role == 'hr'}">
        <a href="reviewStaffRecruitmentRequest.jsp">Review Staff Recruitment Requests</a>
    </c:if>

        <!-- View tasks button: Visible to Production - Decoration Magy-->
    <c:if test="${sessionScope.role == 'magy'}">
        <a href="reviewStaffRecruitmentRequest.jsp">Review Staff Recruitment Requests</a>
    </c:if>

            <!-- View tasks button: Visible to Production - Decorations-->
    <c:if test="${sessionScope.role == 'Decorations' || sessionScope.role == 'Waitress' || sessionScope.role == 'Chef'}">
        <button onclick="window.location.href='/viewMyTasks'">View my tasks</button>
    </c:if>


    <!-- Logout button for all users -->
        <a href="logout" class="btn btn-danger">Logout</a>

</body>
</html>
