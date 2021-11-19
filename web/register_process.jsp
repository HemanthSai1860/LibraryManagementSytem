<%-- 
    Document   : register_process
    Created on : 7 Jun, 2020, 3:11:05 PM
    Author     : student
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
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
            String name=request.getParameter("name");
            String dob=request.getParameter("dob");
            String gender=request.getParameter("gender");
            
            String type=request.getParameter("type");
            String email=request.getParameter("email");
            String password=request.getParameter("password");
            long phno=Long.parseLong(request.getParameter("phno"));
            String acc_id=type+name;
            ResultSet rs=null;
            PreparedStatement ps;
                try{
                    java.sql.Connection con;
                    Class.forName("com.mysql.jdbc.Driver");
                    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectZ","root","rgukt123");
                    ps=con.prepareStatement("select count(*) from account where acc_id=?;");
                    ps.setString(1, acc_id);
                    rs=ps.executeQuery();
                    rs.next();
                    int i=rs.getInt(1);
                    if(i==0)
                    {
                        ps=con.prepareStatement("insert into account values(?,?,?,?,?,?,now(),?);");
                        ps.setString(1, acc_id);
                        ps.setString(2, name);
                        ps.setString(3, dob);
                        ps.setString(4, gender);
                        ps.setLong(5, phno);
                        ps.setString(6, email);
                        ps.setString(7, password);
                        ps.executeUpdate();
                        response.sendRedirect("register_successful.jsp");
                    }
                }
                catch(Exception e) {
                    out.println("error");
                  }    
        %>
        <center><h1>Welcome to RGUKT RK Valley Library</h1></center>
        <br/><br/>
        <center><h3><%=acc_id%> already exists.Try with a different name</h3><br/>
            <h4><a href="login.jsp">go back</a></h4></center>
        
    </body>
</html>
