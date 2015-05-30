<%-- 
    Document   : dashboard
    Created on : May 29, 2015, 2:25:57 PM
    Author     : adampodraza
--%>

<%@page import="com.gaogao.scheduler.persistence.Owner"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% Owner o = (Owner)request.getAttribute("owner"); %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GaoGao Dashboard</title>
    </head>
    <body>
        <h1>Welcome to GaoGao!</h1>
        
        <div>
            Welcome: <%= o.getEmail() %>
        </div>
        
        <div>
            <h2>Dog management console</h2>
            <% for (int i = 0; i < o.getDogList().size(); i ++) {%>
            <%= o.getDogList().get(i) %> </br>
            <% } %>
            
            <h4>Add Dog</h4>
            <form action ="/gaogao/webresources/dog" method="post">
                Dog's name: <input type="text" name="name"/></br>
                Dog's birthday: <input type="text" name="birthday"/></br>
                <input type="submit"/>
            </form>
        </div>
    </body>
</html>
