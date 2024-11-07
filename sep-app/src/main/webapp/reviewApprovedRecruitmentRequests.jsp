<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <c:forEach var="recruitmentRequest" items="${recruitmentRequests}">
            <tr>
                <td>${recruitmentRequest[0]}</td>
                <td>${recruitmentRequest[1]}</td>
                <td>${recruitmentRequest[2]}</td>
                <td>${recruitmentRequest[3]}</td>
                <td>${recruitmentRequest[4]}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>