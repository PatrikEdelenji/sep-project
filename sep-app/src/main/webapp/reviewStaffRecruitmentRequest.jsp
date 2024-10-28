<%@ page import="java.io.*, java.util.*" %>
<%
    String csvFilePath = "C:/Users/hell0/Ivys Documents/KTH Masters/Modern Methods in Software Engineering/Homeworks/Final Project/sep-project/sep-app/data/staffRecruitmentRequests.csv"; // Update with the actual file path
    List<String[]> requests = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            requests.add(line.split(","));
        }
    } catch (IOException e) {
        e.printStackTrace();
        out.println("<p>Error loading recruitment requests.</p>");
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Review Staff Recruitment Requests</title>
</head>
<body>
    <h2>Review Staff Recruitment Requests</h2>
    <table border="1">
        <tr>
            <th>Role</th>
            <th>Position Title</th>
            <th>Department</th>
            <th>Number of Positions</th>
            <th>Justification</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        <%
            for (String[] recruitmentRequest : requests) {
        %>
            <tr>
                <td><%= recruitmentRequest[0] %></td>
                <td><%= recruitmentRequest[1] %></td>
                <td><%= recruitmentRequest[2] %></td>
                <td><%= recruitmentRequest[3] %></td>
                <td><%= recruitmentRequest[4] %></td>
                <td><%= recruitmentRequest[5] %></td>
                <td>
                    <form action="StaffRecruitmentRequestServlet" method="post" style="display:inline;">
                        <input type="hidden" name="actionData" value="<%= recruitmentRequest[1] %>:approve">
                        <label>Approved Status:</label>
                        <select name="status">
                            <option value="Approved">Approved</option>
                            <option value="Disapproved">Disapproved</option>
                        </select>
                        <button type="submit">Approve</button>
                    </form>
                    <form action="StaffRecruitmentRequestServlet" method="post" style="display:inline;">
                        <input type="hidden" name="actionData" value="<%= recruitmentRequest[1] %>:disapprove">
                        <button type="submit">Disapprove</button>
                    </form>
                </td>
            </tr>
        <%
            }
        %>
    </table>
</body>
</html>