<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Tasks</title>
    <style>
        /* General styling */
        body { font-family: Arial, sans-serif; }
        h2 { text-align: center; }
        .section { margin-bottom: 20px; }
        .form-container { max-width: 600px; margin: auto; padding: 20px; border: 1px solid #ddd; border-radius: 5px; }
        label { display: block; margin-top: 10px; font-weight: bold; }
        input, select, textarea { width: 100%; padding: 8px; margin-top: 5px; }
        button { margin-top: 10px; padding: 10px 15px; background-color: #007bff; color: white; border: none; cursor: pointer; }
        button:hover { background-color: #0056b3; }
    </style>
</head>
<body>
    <h2>Create Tasks for Client Record ${param.clientRecord}</h2>
        <!-- PM Role Sections -->
        <c:if test="${sessionScope.role == 'pm'}">
            <div class="form-container">
                <!-- General Section -->
                <c:if test="${general != 'No'}">
                    <div class="section">
                        <h3>General</h3>
                        <form action="submitTask" method="post">
                            <label for="clientRecord">Client Record:</label>
                            <input type="text" name="clientRecord" value="${param.clientRecord}" readonly>

                            <label for="eventType">Event Type:</label>
                            <input type="text" name="eventType" value="${param.eventType}" readonly>

                            <label for="description">Description:</label>
                            <textarea name="description" rows="3"></textarea>

                            <label for="assignee">Assign to:</label>
                            <select name="assignee">
                                <option value="Magy">Magy</option>
                                <option value="Angelina">Angelina</option>
                            </select>

                            <label for="priority">Priority:</label>
                            <select name="priority">
                                <option value="High">High</option>
                                <option value="Medium">Medium</option>
                                <option value="Medium">Low</option>
                            </select>

                            <input type="hidden" name="department" value="General" />
                            <button type="submit">Send Task</button>
                        </form>
                    </div>
                </c:if>
            </div>
            <div class="form-container">
                <!-- Decorations Section -->
                <c:if test="${decorations != 'No'}">
                    <div class="section">
                        <h3>Decorations</h3>
                        <form action="submitTask" method="post">
                            <label for="clientRecord">Client Record:</label>
                            <input type="text" name="clientRecord" value="${param.clientRecord}" readonly>

                            <label for="eventType">Event Type:</label>
                            <input type="text" name="eventType" value="${param.eventType}" readonly>

                            <label for="description">Description:</label>
                            <textarea name="description" rows="3"></textarea>

                            <label for="assignee">Assign to:</label>
                            <select name="assignee">
                                <option value="Magy">Magy</option>
                                <option value="Angelina">Angelina</option>
                                <option value="Don">Don</option>
                                <option value="Tom">Tom</option>
                            </select>

                            <label for="priority">Priority:</label>
                            <select name="priority">
                                <option value="High">High</option>
                                <option value="Medium">Medium</option>
                                <option value="Medium">Low</option>
                            </select>

                            <input type="hidden" name="department" value="Decorations" />
                            <button type="submit">Send Task</button>
                        </form>
                    </div>
                </c:if>
            </div>
            <div class="form-container">
                <c:if test="${photos != 'No'}">
                    <div class="section">
                        <h3>Photograph</h3>
                        <form action="submitTask" method="post">
                            <label for="clientRecord">Client Record:</label>
                            <input type="text" name="clientRecord" value="${param.clientRecord}" readonly>

                            <label for="eventType">Event Type:</label>
                            <input type="text" name="eventType" value="${param.eventType}" readonly>

                            <label for="description">Description:</label>
                            <textarea name="description" rows="3"></textarea>

                            <label for="assignee">Assign to:</label>
                            <select name="assignee">
                                <option value="Tobias">Tobias</option>
                                <option value="Magdalena">Magdalena</option>
                            </select>

                            <label for="priority">Priority:</label>
                            <select name="priority">
                                <option value="High">High</option>
                                <option value="Medium">Medium</option>
                                <option value="Medium">Low</option>
                            </select>

                            <input type="hidden" name="department" value="Photos" />
                            <button type="submit">Send Task</button>
                        </form>
                    </div>
                </c:if>
            </div>
            <div class="form-container">
                <c:if test="${parties != 'No'}">
                    <div class="section">
                        <h3>Music</h3>
                        <form action="submitTask" method="post">
                            <label for="clientRecord">Client Record:</label>
                            <input type="text" name="clientRecord" value="${param.clientRecord}" readonly>

                            <label for="eventType">Event Type:</label>
                            <input type="text" name="eventType" value="${param.eventType}" readonly>

                            <label for="description">Description:</label>
                            <textarea name="description" rows="3"></textarea>

                            <label for="assignee">Assign to:</label>
                            <select name="assignee">
                                <option value="Antony">Antony</option>
                                <option value="Adam">Adam</option>
                            </select>

                            <label for="priority">Priority:</label>
                            <select name="priority">
                                <option value="High">High</option>
                                <option value="Medium">Medium</option>
                                <option value="Medium">Low</option>
                            </select>

                            <input type="hidden" name="department" value="Music" />
                            <button type="submit">Send Task</button>
                        </form>
                    </div>
                </c:if>
            </div>
            <div class="form-container">
                    <div class="section">
                        <h3>Graphic design</h3>
                        <form action="submitTask" method="post">
                            <label for="clientRecord">Client Record:</label>
                            <input type="text" name="clientRecord" value="${param.clientRecord}" readonly>

                            <label for="eventType">Event Type:</label>
                            <input type="text" name="eventType" value="${param.eventType}" readonly>

                            <label for="description">Description:</label>
                            <textarea name="description" rows="3"></textarea>

                            <label for="assignee">Assign to:</label>
                            <select name="assignee">
                                <option value="Julia">Julia</option>
                                <option value="Raymond">Raymond</option>
                            </select>

                            <label for="priority">Priority:</label>
                            <select name="priority">
                                <option value="High">High</option>
                                <option value="Medium">Medium</option>
                                <option value="Medium">Low</option>
                            </select>

                            <input type="hidden" name="department" value="Graphic design" />
                            <button type="submit">Send Task</button>
                        </form>
                    </div>
            </div>
            <div class="form-container">
                <c:if test="${ITdep != 'No'}">
                    <div class="section">
                        <h3>IT Department</h3>
                        <form action="submitTask" method="post">
                            <label for="clientRecord">Client Record:</label>
                            <input type="text" name="clientRecord" value="${param.clientRecord}" readonly>

                            <label for="eventType">Event Type:</label>
                            <input type="text" name="eventType" value="${param.eventType}" readonly>

                            <label for="description">Description:</label>
                            <textarea name="description" rows="3"></textarea>

                            <label for="assignee">Assign to:</label>
                            <select name="assignee">
                                <option value="Christian">Christian</option>
                                <option value="Nicolas">Nicolas</option>
                                <option value="Michael">Michael</option>
                                <option value="Robert">Robert</option>
                            </select>

                            <label for="priority">Priority:</label>
                            <select name="priority">
                                <option value="High">High</option>
                                <option value="Medium">Medium</option>
                                <option value="Medium">Low</option>
                            </select>

                            <input type="hidden" name="department" value="IT Department" />
                            <button type="submit">Send Task</button>
                        </form>
                    </div>
                </c:if>
            </div>
        </c:if>


        <!-- Services Role Sections -->
        <c:if test="${sessionScope.role == 'services'}">
            <div class="form-container">
                <c:if test="${meals != 'No' && drinks != 'No'}">
                    <div class="section">
                        <h3>General</h3>
                        <form action="submitTask" method="post">
                            <label for="clientRecord">Client Record:</label>
                            <input type="text" name="clientRecord" value="${param.clientRecord}" readonly>

                            <label for="eventType">Event Type:</label>
                            <input type="text" name="eventType" value="${param.eventType}" readonly>

                            <label for="description">Description:</label>
                            <textarea name="description" rows="3"></textarea>

                            <label for="assignee">Assign to:</label>
                            <select name="assignee">
                                <option value="Magy">Magy</option>
                                <option value="Angelina">Angelina</option>
                            </select>

                            <label for="priority">Priority:</label>
                            <select name="priority">
                                <option value="High">High</option>
                                <option value="Medium">Medium</option>
                                <option value="Medium">Low</option>
                            </select>

                            <input type="hidden" name="department" value="General" />
                            <button type="submit">Send Task</button>
                        </form>
                    </div>
                </c:if>
            </div>
            <div class="form-container">
                <c:if test="${meals != 'No' && drinks != 'No'}">
                    <div class="section">
                        <h3>Chef</h3>
                        <form action="submitTask" method="post">
                            <label for="clientRecord">Client Record:</label>
                            <input type="text" name="clientRecord" value="${param.clientRecord}" readonly>

                            <label for="eventType">Event Type:</label>
                            <input type="text" name="eventType" value="${param.eventType}" readonly>

                            <label for="description">Description:</label>
                            <textarea name="description" rows="3"></textarea>

                            <label for="assignee">Assign to:</label>
                            <select name="assignee">
                                <option value="Helen">Helen</option>
                                <option value="Diana">Diana</option>
                                <option value="Chris">Chris</option>
                                <option value="Daniel">Daniel</option>
                                <option value="Marilyn">Marilyn</option>
                            </select>

                            <label for="priority">Priority:</label>
                            <select name="priority">
                                <option value="High">High</option>
                                <option value="Medium">Medium</option>
                                <option value="Medium">Low</option>
                            </select>

                            <input type="hidden" name="department" value="Chef" />
                            <button type="submit">Send Task</button>
                        </form>
                    </div>
                </c:if>
            </div>
            <div class="form-container">
                <!-- Repeat similar sections for Photograph, Music, Graphic Design, and Computer Related -->
                <c:if test="${meals != 'No' && drinks != 'No'}">
                    <div class="section">
                        <h3>Waitress</h3>
                        <form action="submitTask" method="post">
                            <label for="clientRecord">Client Record:</label>
                            <input type="text" name="clientRecord" value="${param.clientRecord}" readonly>

                            <label for="eventType">Event Type:</label>
                            <input type="text" name="eventType" value="${param.eventType}" readonly>

                            <label for="description">Description:</label>
                            <textarea name="description" rows="3"></textarea>

                            <label for="assignee">Assign to:</label>
                            <select name="assignee">
                                <option value="Kate">Kate</option>
                                <option value="Lauren">Lauren</option>
                                <option value="Johnny">Johnny</option>
                                <option value="Brad">Brad</option>
                                <option value="Meryl">Meryl</option>
                            </select>

                            <label for="priority">Priority:</label>
                            <select name="priority">
                                <option value="High">High</option>
                                <option value="Medium">Medium</option>
                                <option value="Medium">Low</option>
                            </select>

                            <input type="hidden" name="department" value="Waitress" />
                            <button type="submit">Send Task</button>
                        </form>
                    </div>
                </c:if>
            </div>
        </c:if>

        <div style="text-align: center; margin-top: 20px;">
        <button onclick="window.location.href='/viewClientRecords'" style="padding: 10px 20px; font-size: 16px; background-color: #28a745; color: white; border: none; cursor: pointer;">
            View Client Records
        </button>
    </div>
        
</body>
</html>