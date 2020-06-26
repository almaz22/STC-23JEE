<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="myTags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<myTags:template>
    <jsp:attribute name="header">
        <h1>Access denied</h1>
    </jsp:attribute>
    <jsp:body>
        <strong>Status Code</strong>: ${statusCode}<br/>
        <strong>Requested URI</strong>: ${requestUri}
        <br><br>
        <a href="${pageContext.request.contextPath}/mobiles/login">Login page</a>
    </jsp:body>
</myTags:template>
