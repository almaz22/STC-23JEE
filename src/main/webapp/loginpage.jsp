<%@ page contentType="text/html;charset=UTF-8"%>
<jsp:useBean id="user" class="part2.lesson22.pojo.User" />
<jsp:setProperty name="user" property="username" value="" />
<jsp:setProperty name="user" property="password" value="" />

<h1>Welcome</h1>

<form method="post" action="${pageContext.request.contextPath}/login" autocomplete="off">
    <div class="form-group">
        <label for="username">Login</label>
        <input name="username" type="text" class="form-control" id="username" placeholder="Enter login"
               value="<jsp:getProperty name="user" property="username" />">
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input name="password" type="password" class="form-control" id="password" placeholder="Password"
               value="<jsp:getProperty name="user" property="password" />">
    </div>
    <button type="submit" class="btn btn-primary">OK</button>
</form>

