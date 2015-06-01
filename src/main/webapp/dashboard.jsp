<%--
    Document   : dashboard
    Created on : May 29, 2015, 2:25:57 PM
    Author     : adampodraza
--%>

<%@page import="javax.xml.bind.Marshaller"%>
<%@page import="javax.xml.bind.JAXBContext"%>
<%@page import="com.gaogao.scheduler.messaging.OwnerRequest"%>
<%@page import="java.io.StringWriter"%>
<%@page import="com.gaogao.scheduler.persistence.Owner"%>
<%@page import ="com.gaogao.scheduler.persistence.Dog"%>
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
            <%= o.getDogList().size() %>  </br>
            
            <%= o.getDogList().get(i).toString() %> </br>
            
            <% } %>  
            
            <form action="/gaogao/webresources/dog/create" method="post">
                <input type="text" name="name"/>
                <input type="text" name="birthday"/>
                <input type="hidden" name="owner" value= "<%= o.getEmail() %>"/> 
                <input type="submit"/>
                
            </form>
            
        </div>
    </body>
</html>
