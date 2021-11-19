<%-- 
    Document   : clerk_home_page
    Created on : 6 Jun, 2020, 1:15:55 PM
    Author     : student
--%>

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
        </style>
    </head>
    <body>
        
        <%
            response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
            String acc_id_top_left=(String) session.getAttribute("acc_id_top_left");
            if(acc_id_top_left==null || !(acc_id_top_left.substring(0, 2).equals("cl")))
            {
                response.sendRedirect("login.jsp");
            }
        %>
        <div class="header">
            <p id="acc_id"><%=acc_id_top_left%></p>
            <center><h2><u>RGUKT RK Valley Library Management System</u></h2></center>
        </div>

        <div class="topnav">
            <center> 
                <nav>
                    <a href="clerk_account_details.jsp">Account</a>
                    <a href="Retrive_book" target="_blank">View all books</a>
                    <a href="Issue_book">Issue book</a>
                    <a href="Accept_submission" >Accept submission</a>
                    <a href="Penalty_paid" >Penalty paid</a>
                    <a href="logout_process.jsp" target="_top" style="float: right;">Logout</a>
                </nav>
            </center>
        </div>
        
    </body>
</html>
