<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Budget Request Form</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2>Budget Request Form</h2>
        <form action="budgetRequest" method="post">
            <div class="form-group">
                <label for="projectName">Project Name:</label>
                <input type="text" class="form-control" id="projectName" name="projectName" required>
            </div>
            
            <div class="form-group">
                <label for="currentBudget">Current Budget ($):</label>
                <input type="number" step="0.01" class="form-control" id="currentBudget" name="currentBudget" required>
            </div>
            
            <div class="form-group">
                <label for="requestedAmount">Requested Additional Amount ($):</label>
                <input type="number" step="0.01" class="form-control" id="requestedAmount" name="requestedAmount" required>
            </div>
            
            <div class="form-group">
                <label for="justification">Justification:</label>
                <textarea class="form-control" id="justification" name="justification" rows="4" required></textarea>
            </div>
            
            <button type="submit" class="btn btn-primary">Submit Request</button>
            <a href="menu.jsp" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
</body>
</html>