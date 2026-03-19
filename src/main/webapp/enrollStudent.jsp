<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Enroll Student</title>
</head>
<body>
    <h2>Enroll Student in Course</h2>
    <form action="enrollments" method="post">
        Student:
        <select name="studentId">
            <c:forEach var="s" items="${students}">
                <option value="${s.id}">${s.name}</option>
            </c:forEach>
        </select><br>
        Course:
        <select name="courseId">
            <c:forEach var="c" items="${courses}">
                <option value="${c.id}">${c.title}</option>
            </c:forEach>
        </select><br>
        <input type="submit" value="Enroll">
    </form>
    <c:if test="${param.success != null}">
        <p style="color:green;">Enrollment successful! A confirmation email has been sent (simulated).</p>
    </c:if>
</body>
</html>