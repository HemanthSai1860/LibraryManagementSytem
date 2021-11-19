<%-- 
    Document   : login
    Created on : 11 May, 2020, 8:25:57 PM
    Author     : student
--%>

<%@page import="java.lang.*"%>
<%@page import="java.sql.*"%>
<%@page import="java.lang.*"%>
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

            /* Style the top navigation bar */
            .topnav {
              overflow: hidden;
              background-color: #333;
            }

            /* Style the topnav links */
            .topnav a {
              float: left;
              display: block;
              color: #f2f2f2;
              text-align: center;
              padding: 14px 16px;
              text-decoration: none;
              font-size: 17px;
            }

            /* Change color on hover */
            .topnav a:hover {
              background-color: #ddd;
              color: black;
            }


            /* Footer */
            .footer {
              padding: 20px;
              text-align: center;
              background: #ddd;
              float: top;
            }
            
            .container {
                border-radius: 5px;
                background-color: #f0f0f0;
                padding: 20px;
                
              }

              .col-left {
                float: left;
                width: 50%;
                margin-top: 6px;
                text-align: right;
              }

              .col-right {
                float: left;
                width: 50%;
                margin-top: 6px;
                text-align: left;
              }

              /* Clear floats after the columns */
              .row:after {
                content: "";
                display: table;
                clear: both;
              }
              
              input[type=submit] {
                background-color: #4CAF50;
                color: white;
                padding: 12px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                float: center;
              }

              input[type=submit]:hover {
                background-color: #45a049;
              }
        </style>
    </head>
    <body>
        
        <%
            response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
            String acc_id_top_left=(String)session.getAttribute("acc_id_top_left");
            if(acc_id_top_left!=null)
            {
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
                        default :  response.sendRedirect("login.jsp");
                    }
            }
        %>
        

        
        <div class="header">
            <center><h1>Welcome to RGUKT RK Valley Library</h1></center>
        </div>
        <br/><br/>
        <div class="container">
            <center><h3><u>Login</u></h3></center>
            <center>
            <form name="hello" action="login_process.jsp" method="POST">
                Enter acc id:<br/><input type="text" name="acc_id" required/><br/>
                Enter password:<br/><input type="password" name="password" required/><br/>
                <input type="submit" value="login"/>
            </form>
                <br/>
                <a href="register.jsp">Not a user,chick here to register</a>
            </center>
        </div>
    </body>
</html>
