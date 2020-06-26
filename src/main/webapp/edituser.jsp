<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="user" scope="request" class="part2.lesson22.pojo.User" />

<jsp:setProperty name="user" property="id" value="${user.id}" />
<jsp:setProperty name="user" property="username" value="${user.username}" />
<jsp:setProperty name="user" property="password" value="${user.password}" />

<h1>Edit user</h1>
<form method="post" action="${pageContext.request.contextPath}/edituser" autocomplete="off">
    <div class="form-group">
        <label for="id">ID</label>
        <input name="id" type="text" class="form-control" id="id"
               value="<jsp:getProperty name="user" property="id" />">
    </div>
    <div class="form-group">
        <label for="username">Username</label>
        <input name="username" type="text" class="form-control" id="username" placeholder="${user.username}"
               value="<jsp:getProperty name="user" property="username" />">
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input name="password" type="text" class="form-control" id="password" placeholder="${user.password}"
               value="<jsp:getProperty name="user" property="password" />">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
