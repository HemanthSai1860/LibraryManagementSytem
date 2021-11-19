<%-- 
    Document   : request_cancel
    Created on : 6 Jun, 2020, 1:18:44 PM
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
            if(acc_id_top_left==null || !(acc_id_top_left.substring(0,1).equals("u")))
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
                <a href="user_account_details.jsp">Account</a>
                <a href="Your_books">your books</a>
                <a href="Retrive_book" target="_blank" >view all books</a>
                <a href="Req_res" >Request/Reserve</a>
                <a href="View_requests">view requests</a>
                <a href="View_reservations">view reservations</a>
                <a href="logout_process.jsp"  style="float: right;" target="_top">Logout</a>
                </nav>
            </center>
        </div>
        <h4>your request is cancelled</h4>
    </body>
</html>
