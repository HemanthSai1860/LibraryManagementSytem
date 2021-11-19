<%-- 
    Document   : login_process
    Created on : 6 Jun, 2020, 2:51:53 PM
    Author     : student
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body {
              margin: 0;
            }

            /* Style the header */
            .header {
              padding: 10px;
              background: skyblue;
              color: black;
              font-size: 17px;
            }
        </style>
    </head>
    <body>
        <% 
            response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
            String acc_id_top_left=request.getParameter("acc_id");
            String pass=request.getParameter("password");
            String acc_id_top_left_temp=(String)session.getAttribute("acc_id_top_left");
            if(acc_id_top_left_temp==null)
            {

                ResultSet rs=null;
                int co=0,err=0;
                PreparedStatement ps;
                    try{
                        Connection con;
                        err=0;
                        Class.forName("com.mysql.jdbc.Driver");
                        err=1;
                        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectZ","root","rgukt123");
                        err=2;
                        ps=con.prepareStatement("select * from account where acc_id=?;");
                        err=3;
                        co=1;
                        ps.setString(1, acc_id_top_left);
                        co=2;
                        rs=ps.executeQuery();
                        co=3;

                        if(rs.next())
                        {
                            co=4;
                            String password=rs.getString("password");
                            co=5;
                            if(pass.equals(password))
                            {
                                co=6;
                                session.setAttribute("acc_id_top_left", acc_id_top_left);
                                co=7;
                                switch(acc_id_top_left.substring(0, 2))
                                {    
                                    case "uu": response.sendRedirect("user_home_page.jsp");
                                       break;
                                    case "up": response.sendRedirect("user_home_page.jsp");
                                               break;
                                    case "uf": response.sendRedirect("user_home_page.jsp");
                                               break;
                                    case "ur": response.sendRedirect("user_home_page.jsp");
                                               break;   
                                    case "cl": response.sendRedirect("clerk_home_page.jsp");
                                               break;
                                    case "ad": response.sendRedirect("admin_home_page.jsp");
                                               break;
                                }
                            }

                        }
                    }
                    catch(Exception e) {out.println(err+"error "+co+e);} 
            }
            else
                response.sendRedirect("login.jsp");
        %>
        <div class="header">
            <center><h1>Welcome to RGUKT RK Valley Library</h1></center>
        </div>
        <br/><br/>
        <center><h4>Incorrect Username or Password</h4>
        <h5><a href="login.jsp">click here to go back</a></h5>
        </center>
    </body>
</html>
