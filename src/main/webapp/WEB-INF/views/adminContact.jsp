<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Contact Messages Management | Quiz App</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h2>Contact Messages Management</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Contact ID</th>
            <th>Subject</th>
            <th>Message</th>
            <th>Email</th>
            <th>Time</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="message" items="${messages}">
            <tr>
                <td>${message.contactId}</td>
                <td>${message.subject}</td>
                <td>${message.message}</td>
                <td>${message.email}</td>
                <td>${message.time}</td>
                <td>
                    <a href="<%= request.getContextPath() %>/admin/delete-contact/${message.contactId}" class="btn btn-danger btn-sm">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
