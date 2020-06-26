<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All users</title>
</head>
<table class="table">
    <thead>
    <tr>
        <th>ID</th>
        <th>username</th>
        <th>password</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td scope="row">${user.id}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td><a href="${pageContext.request.contextPath}/edituser?id=${user.id}">Edit</a></td>
            <td><a href="${pageContext.request.contextPath}/deleteuser?id=${user.id}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br>
<a href="/mobiles/">Main page</a>
</html>
