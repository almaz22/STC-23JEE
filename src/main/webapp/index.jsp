<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="myTags" tagdir="/WEB-INF/tags" %>

<myTags:template>
    <jsp:attribute name="header">
        <h1>Mobiles</h1>
    </jsp:attribute>
    <jsp:body>
        <ul>
            <li><a href="${pageContext.request.contextPath}/allmobiles">List mobiles</a></li>
            <li><a href="${pageContext.request.contextPath}/addmobile">Add mobile</a></li>
            <br><br>
            <li><a href="${pageContext.request.contextPath}/allusers">List users</a></li>
            <li><a href="${pageContext.request.contextPath}/adduser">Add user</a></li>
            <br><br>
            <a href="${pageContext.request.contextPath}/login">Logout</a>
        </ul>
    </jsp:body>
</myTags:template>