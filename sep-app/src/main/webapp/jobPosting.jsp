<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Job Posting</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2>Create Job Posting</h2>
        <form action="jobPosting" method="post">
            <div class="form-group">
                <label for="jobTitle">Job Title:</label>
                <input type="text" class="form-control" id="jobTitle" name="jobTitle" required>
            </div>

            <div class="form-group">
                <label for="department">Department:</label>
                <input type="text" class="form-control" id="department" name="department" required>
            </div>

            <div class="form-group">
                <label for="jobDescription">Job Description:</label>
                <textarea class="form-control" id="jobDescription" name="jobDescription" rows="4" required></textarea>
            </div>

            <div class="form-group">
                <label for="qualifications">Qualifications:</label>
                <textarea class="form-control" id="qualifications" name="qualifications" rows="4" required></textarea>
            </div>

            <div class="form-group">
                <label for="salaryRange">Salary Range ($):</label>
                <input type="text" class="form-control" id="salaryRange" name="salaryRange" required>
            </div>

            <button type="submit" class="btn btn-primary">Submit Job Posting</button>
            <a href="menu.jsp" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
</body>
</html>