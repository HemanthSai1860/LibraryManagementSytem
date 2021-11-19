<%-- 
    Document   : user_account_details
    Created on : 9 Jun, 2020, 4:03:43 PM
    Author     : student
--%>


<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
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
            String acc_id="",name="",dob="",gender="",phno="",email="",reg_dt="";
            response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
            String acc_id_top_left=(String) session.getAttribute("acc_id_top_left");
            String sessionid=session.getId();
            if(acc_id_top_left==null || !(acc_id_top_left.substring(0,1).equals("u")))
            {
                response.sendRedirect("login.jsp");
            }
            else{
                
                ResultSet rs=null;
                PreparedStatement ps;
                try{
                    Connection con;
                    Class.forName("com.mysql.jdbc.Driver");
                    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectZ","root","rgukt123");
                    ps=con.prepareStatement("select * from account where acc_id=?;");
                    ps.setString(1, acc_id_top_left);
                    rs=ps.executeQuery();
                    if(rs.next())
                    {
                        acc_id=rs.getString(1);
                        name=rs.getString(2);
                        dob=rs.getString(3);
                        gender=rs.getString(4);
                        phno=rs.getString(5);
                        email=rs.getString(6);
                        reg_dt=rs.getString(7);
                    }
                }
                catch(Exception e) {out.println("error");}
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
        
    <center>
        <br/>
            <u><h2>Your Account details</h2></u>
            <br/>
        <div class="container">
            <div class="row">
                <div class="col-left">
                    <b>Account id:</b>
                </div>
                <div class="col-right">
                    <%= acc_id %>
                </div>
            </div>
            <div class="row">
                <div class="col-left">
                    <b>Name:</b>
                </div>
                <div class="col-right">
                    <%= name%>
                </div>
            </div>
            <div class="row">
                <div class="col-left">
                    <b>Date of Birth:</b>
                </div>
                <div class="col-right">
                    <%= dob%>
                </div>
            </div>
            <div class="row">
                <div class="col-left">
                    <b>Gender:</b>
                </div>
                <div class="col-right">
                    <%= gender%>
                </div>
            </div>
            <div class="row">
                <div class="col-left">
                    <b>Phone number:</b>
                </div>
                <div class="col-right">
                    <%= phno%>
                </div>
            </div>
            <div class="row">
                <div class="col-left">
                    <b>Email id:</b>
                </div>
                <div class="col-right">
                    <%= email%>
                </div>
            </div>
            <div class="row">
                <div class="col-left">
                    <b>Registered Date and Time:</b>
                </div>
                <div class="col-right">
                    <%= reg_dt%>
                </div>
            </div>
        </div>
    </center>
    <br/>
    <center>
        <a href="user_account_details_update.jsp">Edit</a>
    </center>
    </body>
</html>
