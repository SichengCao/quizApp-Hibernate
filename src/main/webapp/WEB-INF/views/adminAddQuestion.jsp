<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Add New Question</title>
</head>
<body>
<h2>Add New Question</h2>

<!-- 显示错误或成功消息 -->
<c:if test="${not empty errorMessage}">
    <p style="color: red;">${errorMessage}</p>
</c:if>
<c:if test="${not empty successMessage}">
    <p style="color: green;">${successMessage}</p>
</c:if>

<form action="${pageContext.request.contextPath}/admin/add-question" method="post">
    <label>Question:</label>
    <input type="text" name="questionText" required /><br>

    <label>Option A:</label>
    <input type="text" name="optionA" required /><br>

    <label>Option B:</label>
    <input type="text" name="optionB" required /><br>

    <label>Option C:</label>
    <input type="text" name="optionC" required /><br>

    <label>Option D:</label>
    <input type="text" name="optionD" required /><br>

    <label>Correct Answer (A/B/C/D):</label>
    <input type="text" name="correctOption" required pattern="[A-Da-d]" title="Enter A, B, C, or D" /><br>

    <label>Category:</label>
    <select name="categoryId" required>
        <option value="">-- Select Category --</option>
        <c:forEach var="category" items="${categories}">
            <option value="${category.id}"></option>
        </c:forEach>
    </select>
    <br>

    <button type="submit">Save Question</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/admin/questions">⬅ Back to Questions</a>
</body>
</html>
