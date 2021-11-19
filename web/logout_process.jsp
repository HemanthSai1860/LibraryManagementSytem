<%-- 
    Document   : logout_process
    Created on : 7 Jun, 2020, 9:14:38 AM
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <%
            response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
            session.invalidate();
            response.sendRedirect("login.jsp");
        %>
        
        <h1>Hello World!</h1>
    </body>
</html>
