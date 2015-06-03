<%--
    Document   : dashboard
    Created on : May 29, 2015, 2:25:57 PM
    Author     : adampodraza
--%>

<%@page import="com.gaogao.scheduler.persistence.ServiceProvider"%>
<%@page import="com.gaogao.scheduler.persistence.Event"%>
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
            <h2>Dog Management Console</h2>
            <div>
            <% for (int i = 0; i < o.getDogList().size(); i ++) {%>
            <br>
            
            Name: <%= o.getDogList().get(i).getName() %> </br>
            
            <% } %>  
            </div>
            <br>
            <br>
            
            <div>
                <h2>Add Dog</h2>
            <form action="/gaogao/webresources/dog/create" method="post">
                Please enter your dog's name: <br>
                <input type="text" name="name"/> <br>
                Please enter your dog's birthday: <br>
                <input type="text" name="birthday"/> <br>
                <input type="hidden" name="owner" value= "<%= o.getEmail() %>"/> 
                <input type="submit"/>
                
            </form>
            </div>
                <div>
                <h2>Event Management Console</h2>
                <div>
                    
                    <% for(Dog d: o.getDogList()) { %>
                        
                        <% for(Event e: d.getEventList()) { %>
                        <%= e.getDescription() %> Completed: <%= e.isCompleted() %>
                        <% } %>
                    <%} %>
                </div>
                
                <h2>Add Event For Your Dog </h2>
                <form action="/gaogao/webresources/event/create" method="post">
                    Please enter your dog's name: <br>
                    <input type="text" name="name" />
                    <br>
                    Please enter the event's description: <br>
                    <input type="text" name="description"/>
                    <br>
                    Please enter the event's date: <br>
                    <input type="text" name="date"/>
                    <input type="hidden" name="owner" value= "<%= o.getEmail() %>"/>
                    <br>
                    <input type="submit"/>
                    
                </form>
                </div>
                    
                    <div>
                        <h2>Service Provider Management Console</h2>
                        
                        
                            <% for(ServiceProvider sp : o.getProviderList()) { %>
                            <h3><%= sp.getName() %> </h3>
                            <%= sp.getEmail() %>
                            <%= sp.getPhoneNumber() %>
                            <% } %>
                        
                        
                        
                        <h2>Add Service Provider For Your Dog</h2>
                        <form action="/gaogao/webresources/kennel/create" method="post">
                            Please enter the name:
                            <input type="text" name="name"/>
                            <br>
                            Please enter the email:
                            <input type="text" name="email"/>
                            <br>
                            Please enter the phone number:
                            <input type="text" name="number"/>
                            <br>
                            <input type="hidden" name="owner" value="<%=o.getEmail()%>"/>
                            <br>
                            <input type="submit"/>
                        </form>
                        
                    </div>
                        
                        
        </div>
    </body>
</html>
