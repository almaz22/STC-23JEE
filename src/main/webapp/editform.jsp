<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:useBean id="mobile" scope="request" class="part2.lesson22.pojo.Mobile" />

<c:set target="${mobile}" property="id" value="${mobile.id}" />
<c:set target="${mobile}" property="model" value="${mobile.model}" />
<c:set target="${mobile}" property="price" value="${mobile.price}" />
<c:set target="${mobile}" property="manufacturer" value="${mobile.manufacturer}" />

<h1>Edit mobile</h1>
<form method="post" action="${pageContext.request.contextPath}/editmobile" autocomplete="off">
    <div class="form-group">
        <label for="id">ID</label>
        <input name="id" type="text" class="form-control" id="id"
               value="<jsp:getProperty name="mobile" property="id" />">
    </div>
    <div class="form-group">
        <label for="model">Model</label>
        <input name="model" type="text" class="form-control" id="model" placeholder="${mobile.model}"
               value="<jsp:getProperty name="mobile" property="model" />">
    </div>
    <div class="form-group">
        <label for="price">Price</label>
        <input name="price" type="text" class="form-control" id="price" placeholder="${mobile.price}"
               value="<jsp:getProperty name="mobile" property="price" />">
    </div>
    <div class="form-group">
        <label for="manufacturer">Manufacturer</label>
        <input name="manufacturer" type="text" class="form-control" id="manufacturer" placeholder="${mobile.manufacturer}"
               value="<jsp:getProperty name="mobile" property="manufacturer" />">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>

