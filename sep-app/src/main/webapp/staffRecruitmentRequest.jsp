<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Staff Recruitment Request</title>
</head>
<body>
    <h2>Create Staff Recruitment Request</h2>
    <form action="staffRecruitmentRequest" method="post">
        <label for="positionTitle">Position Title:</label>
        <input type="text" name="positionTitle" required><br><br>

        <label for="department">Department:</label>
        <input type="text" name="department" required><br><br>

        <label for="numberOfPositions">Number of Positions:</label>
        <input type="number" name="numberOfPositions" required><br><br>

        <label for="justification">Justification:</label>
        <textarea name="justification" required></textarea><br><br>

        <input type="submit" value="Submit Request">
    </form>
</body>
</html>