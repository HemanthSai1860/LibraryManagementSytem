<%-- 
    Document   : account_details_update_confirm
    Created on : 14 Jun, 2020, 11:18:17 AM
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
            String acc_id="",name="",dob="",gender="",email="",pass="";
            StringBuffer url=new StringBuffer("");
            int co=0;
            acc_id=request.getParameter("acc_id");
            response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
            String acc_id_top_left=(String) session.getAttribute("acc_id_top_left");
            if(acc_id_top_left==null || !(acc_id_top_left.equals(acc_id)))
            {
                response.sendRedirect("login.jsp");
            }
            else{
                
                name=request.getParameter("name");
                dob=request.getParameter("dob");
                gender=request.getParameter("gender");
                long phno=Long.parseLong(request.getParameter("phno"));
                email=request.getParameter("email");
                pass=request.getParameter("password");
                ResultSet rs=null;
                PreparedStatement ps;
                try{
                    java.sql.Connection con;
                    Class.forName("com.mysql.jdbc.Driver");
                    co=1;
                    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectZ","root","rgukt123");
                    ps=con.prepareStatement("update account set name=?,dob=?,gender=?,phno=?,email=?,password=? where acc_id=?;");
                    co=2;
                    ps.setString(1, name);
                    ps.setString(2, dob);
                    ps.setString(3, gender);
                    ps.setLong(4, phno);
                    ps.setString(5, email);
                    ps.setString(6, pass);
                    ps.setString(7, acc_id_top_left);
                    co=3;
                    ps.executeUpdate();
                    co=4;
                }
                catch(Exception e) {out.println(co+"error");} 
              url=request.getRequestURL();
              String urlstr=url.toString();
              response.sendRedirect(urlstr);
            }
            
        %>
        
        <h1><%= url %></h1>
    </body>
</html>
