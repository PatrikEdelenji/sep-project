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
</body>
</html>
