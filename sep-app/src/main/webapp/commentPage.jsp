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
        <form action="updateTaskComment" method="post">
            <fieldset>
                <legend>Task information</legend>
                <label for="clientRecord">Project record:</label>
                <input type="text" id="clientRecord" name="clientRecord" value="${clientRecord}" readonly>
                
                <label for="eventType">Event type:</label>
                <input type="text" id="eventType" name="eventType" value="${eventType}" readonly>
                
                <label for="taskDescription">Task description:</label>
                <input type="text" id="taskDescription" name="taskDescription" value="${taskDescription}" readonly>
                
                <label for="assignee">Assignee:</label>
                <input type="text" id="assignee" name="assignee" value="${assignee}" readonly>
                
                <label for="priority">Priority:</label>
                <input type="text" id="priority" name="priority" value="${priority}" readonly>

                <label for="sender">Sender:</label>
                <input type="text" id="sender" name="sender" value="${sender}" readonly>

                <label for="type">Type:</label>
                <input type="text" id="type" name="type" value="${type}" readonly>
            </fieldset>

            <fieldset>
                <legend>Comment</legend>
            
                <label for="comments">Comments:</label>
                <textarea id="comments" name="comments">${comments != '-' ? budgetReview : ''}</textarea>
            </fieldset>

            <button type="submit">Submit</button>
        </form>
    </div>
</body>
</html>