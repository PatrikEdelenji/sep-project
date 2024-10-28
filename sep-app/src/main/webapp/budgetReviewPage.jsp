<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Budget Review</title>
    <style>
        /* Styling for the form */
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { display: flex; align-items: center; justify-content: center; height: 100vh; background-color: #f7f7f7; font-family: Arial, sans-serif; }
        h2 { text-align: center; margin-bottom: 20px; }
        .form-container { width: 100%; max-width: 500px; padding: 20px; background-color: #ffffff; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); }
        fieldset { border: none; margin-bottom: 15px; }
        legend { font-weight: bold; font-size: 1.1em; margin-bottom: 10px; }
        label { display: block; font-weight: bold; margin-top: 10px; color: #333; }
        input[type="text"], input[type="date"], input[type="number"], textarea { width: 100%; padding: 8px; margin-top: 5px; border: 1px solid #ccc; border-radius: 4px; }
        input[readonly], textarea[readonly] { background-color: #e9ecef; } /* Read-only fields styling */
        button { width: 100%; padding: 10px; font-size: 1em; color: white; background-color: #007bff; border: none; border-radius: 4px; cursor: pointer; margin-top: 15px; }
        button:hover { background-color: #0056b3; }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Budget Review</h2>
        <form action="updateBudgetReview" method="post">
            <fieldset>
                <legend>Client Information</legend>
                <label for="clientRecord">Record Number:</label>
                <input type="text" id="clientRecord" name="clientRecord" value="${clientRecord}" readonly>

                <label for="clientName">Client Name:</label>
                <input type="text" id="clientName" name="clientName" value="${clientName}" readonly>
            </fieldset>

            <fieldset>
                <legend>Event Details</legend>
                <label for="eventType">Event Type:</label>
                <input type="text" id="eventType" name="eventType" value="${eventType}" readonly>
                
                <label for="fromDate">From:</label>
                <input type="date" id="fromDate" name="fromDate" value="${fromDate}" readonly>
                
                <label for="toDate">To:</label>
                <input type="date" id="toDate" name="toDate" value="${toDate}" readonly>
                
                <label for="expectedAttendees">Expected number of attendees:</label>
                <input type="number" id="expectedAttendees" name="expectedAttendees" value="${expectedAttendees}" readonly>
            </fieldset>

            <fieldset class="preferences">
                <legend>Preferences</legend>
                <label><input type="checkbox" name="decorations" value="Yes" ${decorations == 'Yes' ? 'checked' : ''} onclick="return false;"> Decorations</label>
                <label><input type="checkbox" name="parties" value="Yes" ${parties == 'Yes' ? 'checked' : ''} onclick="return false;"> Parties</label>
                <label><input type="checkbox" name="photos" value="Yes" ${photos == 'Yes' ? 'checked' : ''} onclick="return false;"> Photos/filming</label>
                <label><input type="checkbox" name="meals" value="Yes" ${meals == 'Yes' ? 'checked' : ''} onclick="return false;"> Breakfast, lunch, dinner</label>
                <label><input type="checkbox" name="drinks" value="Yes" ${drinks == 'Yes' ? 'checked' : ''} onclick="return false;"> Soft/hot drinks</label>
            </fieldset>

            <fieldset>
                <legend>Budget</legend>
                <label for="budget">Expected Budget:</label>
                <input type="number" id="budget" name="budget" value="${budget}" readonly>

                <label for="budgetDescription">Budget Review:</label>
                <textarea id="budgetDescription" name="budgetDescription">${budgetReview != 'PENDING' ? budgetReview : ''}</textarea>
            </fieldset>

            <button type="submit">Submit</button>
        </form>
    </div>
</body>
</html>