<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Client Record</title>
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
        button { width: 100%; padding: 10px; font-size: 1em; color: white; background-color: #007bff; border: none; border-radius: 4px; cursor: pointer; margin-top: 15px; }
        button:hover { background-color: #0056b3; }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Event request</h2>
        <form action="submitNewClientRecord" method="post">
            <fieldset>
                <legend>Client Information</legend>
                <label for="clientRecord">Record Number:</label>
                <input type="text" id="clientRecord" name="clientRecord" value="${clientRecord}" readonly>

                <label for="clientName">Client Name:</label>
                <input type="text" id="clientName" name="clientName" value="${clientName}">
            </fieldset>

            <fieldset>
                <legend>Event Details</legend>
                <label for="eventType">Event Type:</label>
                <input type="text" id="eventType" name="eventType" value="${eventType}">

                <label for="eventDescription">Event Description:</label>
                <textarea id="eventDescription" name="eventDescription">${eventDescription}</textarea>

                <label for="fromDate">From:</label>
                <input type="date" id="fromDate" name="fromDate" value="${fromDate}">

                <label for="toDate">To:</label>
                <input type="date" id="toDate" name="toDate" value="${toDate}">

                <label for="expectedAttendees">Expected number of attendees:</label>
                <input type="number" id="expectedAttendees" name="expectedAttendees" value="${expectedAttendees}">
            </fieldset>

<fieldset>
    <legend>Preferences Descriptions</legend>

    <!-- Conditionally display each description field based on preference -->
    <c:if test="${decorations == 'Yes'}">
        <p>Field should be visible</p>
        <p>Decorations: ${decorations}</p>
        <label for="decorationsDescription">Decorations Description:</label>
        <textarea id="decorationsDescription" name="decorationsDescription">${decorationsDescription}</textarea>
    </c:if>

    <c:if test="${parties == 'Yes'}">
        <p>Field should be visible</p>
        <p>Parties: ${parties}</p>
        <label for="partiesDescription">Parties Description:</label>
        <textarea id="partiesDescription" name="partiesDescription">${partiesDescription}</textarea>
    </c:if>

    <c:if test="${photos == 'Yes'}">
        <p>Field should be visible</p>
        <p>Photos: ${photos}</p>
        <label for="photosDescription">Photos/Filming Description:</label>
        <textarea id="photosDescription" name="photosDescription">${photosDescription}</textarea>
    </c:if>

    <c:if test="${meals == 'Yes'}">
        <p>Field should be visible</p>
        <p>Meals: ${meals}</p>
        <label for="mealsDescription">Meals Description:</label>
        <textarea id="mealsDescription" name="mealsDescription">${mealsDescription}</textarea>
    </c:if>

    <c:if test="${drinks == 'Yes'}">
        <p>Field should be visible</p>
        
        <label for="drinksDescription">Drinks Description:</label>
        <textarea id="drinksDescription" name="drinksDescription">${drinksDescription}</textarea>
    </c:if>
</fieldset>

            <fieldset>
                <legend>Budget</legend>
                <label for="budget">Expected Budget:</label>
                <input type="number" id="budget" name="budget" value="${budget}">
            </fieldset>

            <button type="submit">Submit</button>
        </form>
    </div>
</body>
</html>