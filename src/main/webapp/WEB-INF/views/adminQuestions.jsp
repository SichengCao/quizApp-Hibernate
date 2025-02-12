<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Questions Management</title>
</head>
<body>
<h2>Questions Management</h2>

<a href="${pageContext.request.contextPath}/admin/add-question">➕ Add New Question</a>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Question</th>
        <th>Category ID</th> <!-- 关联的 Category -->
        <th>Option A</th>
        <th>Option B</th>
        <th>Option C</th>
        <th>Option D</th>
        <th>Correct Answer</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="question" items="${questions}">
        <tr>
            <td>${question.id}</td>
            <td>${question.questionText}</td>
            <td>${question.category.categoryId}</td> <!-- 显示 category_id -->
            <td>${question.optionA}</td>
            <td>${question.optionB}</td>
            <td>${question.optionC}</td>
            <td>${question.optionD}</td>
            <td>${question.correctOption}</td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/delete-question/${question.id}"
                   onclick="return confirm('Are you sure?')">❌ Delete</a>
            </td>
        </tr>
    </c:forEach>

</table>
</body>
</html>