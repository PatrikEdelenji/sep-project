<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Request for Event Planning</title>
    <style>
        /* Styling for the form */
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { display: flex; align-items: center; justify-content: center; height: 100vh; background-color: #f7f7f7; font-family: Arial, sans-serif; }
        h2 { text-align: center; margin-bottom: 20px; }
        .form-container { width: 100%; max-width: 500px; padding: 20px; background-color: #ffffff; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); }
        fieldset { border: none; margin-bottom: 15px; }
        legend { font-weight: bold; font-size: 1.1em; margin-bottom: 10px; }
        label { display: block; font-weight: bold; margin-top: 10px; color: #333; }
        input[type="text"], input[type="date"], input[type="number"] { width: 100%; padding: 8px; margin-top: 5px; border: 1px solid #ccc; border-radius: 4px; }
        input[type="radio"] { margin-right: 5px; }
        button { width: 100%; padding: 10px; font-size: 1em; color: white; background-color: #007bff; border: none; border-radius: 4px; cursor: pointer; margin-top: 15px; }
        button:hover { background-color: #0056b3; }
        .preferences label { display: inline-block; width: 45%; margin-top: 10px; }
    </style>
    <script>
        function generateClientRecord() {
            const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
            let result = '';
            for (let i = 0; i < 5; i++) {
                result += characters.charAt(Math.floor(Math.random() * characters.length));
            }
            return result;
        }

        function toggleClientRecord(isNewClient) {
            const clientRecordInput = document.getElementById("clientRecord");
            if (isNewClient) {
                clientRecordInput.value = generateClientRecord();
                clientRecordInput.readOnly = true; // Keep as readonly for submission
            } else {
                clientRecordInput.value = ""; // Clear for manual input
                clientRecordInput.readOnly = false;
            }
        }

        window.onload = function() {
            toggleClientRecord(true); // Default to New Client
        };
    </script>
</head>
<body>
    <div class="form-container">
        <h2>Request for Event Planning</h2>
        <form action="submitNewEventRequest" method="post">
            <fieldset>
                <legend>Client Information</legend>

                <!-- Client type selection -->
                <label>
                    <input type="radio" name="clientType" value="new" onclick="toggleClientRecord(true)" checked>
                    New Client
                </label>
                <label>
                    <input type="radio" name="clientType" value="existing" onclick="toggleClientRecord(false)">
                    Existing Client
                </label>

                <!-- Client Record Number -->
                <label for="clientRecord">Record Number:</label>
                <input type="text" id="clientRecord" name="clientRecord" readonly>

                <!-- Client Name -->
                <label for="clientName">Client Name:</label>
                <input type="text" id="clientName" name="clientName">
            </fieldset>

            <fieldset>
                <legend>Event Details</legend>
                <label for="eventType">Event Type:</label>
                <input type="text" id="eventType" name="eventType">
                
                <label for="fromDate">From:</label>
                <input type="date" id="fromDate" name="fromDate">
                
                <label for="toDate">To:</label>
                <input type="date" id="toDate" name="toDate">
                
                <label for="expectedAttendees">Expected number of attendees:</label>
                <input type="number" id="expectedAttendees" name="expectedAttendees">
            </fieldset>

            <fieldset class="preferences">
                <legend>Preferences</legend>
                <label><input type="checkbox" name="preferences" value="decorations"> Decorations</label>
                <label><input type="checkbox" name="preferences" value="parties"> Parties</label>
                <label><input type="checkbox" name="preferences" value="photos"> Photos/filming</label>
                <label><input type="checkbox" name="preferences" value="meals"> Breakfast, lunch, dinner</label>
                <label><input type="checkbox" name="preferences" value="drinks"> Soft/hot drinks</label>
            </fieldset>

            <fieldset>
                <legend>Budget</legend>
                <label for="budget">Expected Budget:</label>
                <input type="number" id="budget" name="budget">
            </fieldset>

            <button type="submit">Submit</button>
        </form>
    </div>
</body>
</html>
