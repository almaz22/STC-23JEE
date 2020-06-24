<jsp:useBean id="mobile" scope="request" type="part2.lesson22.pojo.Mobile"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<h1>Mobile Info</h1>
<ul class="list-group">
   <li class="list-group-item">${mobile.id}</li>
   <li class="list-group-item">${mobile.model}</li>
   <li class="list-group-item">${mobile.price}</li>
   <li class="list-group-item">${mobile.manufacturer}</li>
</ul>

<br>
<a href="/mobiles/">Main page</a>
