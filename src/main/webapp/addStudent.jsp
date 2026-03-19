<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Student</title>
</head>
<body>
    <h2>Add Student</h2>
    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>
    <form action="students?action=insert" method="post">
        Name: <input type="text" name="name" required><br>
        Email: <input type="email" name="email" required><br>
        Phone: <input type="text" name="phone"><br>
        Student ID: <input type="text" name="studentId" required><br>
        Enrollment Date: <input type="date" name="enrollmentDate" required><br>
        <input type="submit" value="Save">
    </form>
    <a href="students">Back to List</a>
</body>
</html>