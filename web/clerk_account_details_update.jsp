<%-- 
    Document   : clerk_account_details_update
    Created on : 14 Jun, 2020, 10:59:25 AM
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
                background-color: #f5f5f5;
                padding: 20px;
                
              }

              .col-left {
                float: left;
                width: 45%;
                margin-top: 6px;
                text-align: right;
              }

              .col-right {
                float: left;
                width: 55%;
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
              
              input[type=text],input[type=number],input[type=email],input[type=password],input[type=date], select, textarea {
                  width: 30%;
                  padding: 5px;
                  border: 1px solid black;
                  border-radius: 2px;
                  resize: vertical;
                  margin-top: 6px;
                  background-color: lightgoldenrodyellow;
                  }
                  
              input[type=radio]
              {
                  margin-top: 15px;
              }
                  
              * {
                  box-sizing: border-box;
              }
              label {
                    padding: 12px 12px 12px 0;
                    display: inline-block;
              }
              
              select {
                appearance: none;
                outline: 0;
                background: lightgray;
                background-image: none;
                color: black;
                cursor: pointer;
                border:1px solid black;
                border-radius:3px;
            }
            
        </style>
    </head>
    <body>
        <%
            String acc_id="",name="",dob="",gender="",phno="",email="",reg_dt="";
            response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
            String acc_id_top_left=(String) session.getAttribute("acc_id_top_left");
            String sessionid=session.getId();
            if(acc_id_top_left==null || !(acc_id_top_left.substring(0, 2).equals("cl")))
            {
                response.sendRedirect("login.jsp");
            }
            else{
                
                ResultSet rs=null;
                PreparedStatement ps;
                try{
                    java.sql.Connection con;
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
                    <a href="clerk_account_details.jsp">Account</a>
                    <a href="Retrive_book" target="_blank">View all books</a>
                    <a href="Issue_book">Issue book</a>
                    <a href="Accept_submission" >Accept submission</a>
                    <a href="Penalty_paid" >Penalty paid</a>
                    <a href="logout_process.jsp" target="_top" style="float: right;">Logout</a>
                </nav>
            </center>
        </div>
    <center>
        <br/>
            <u><h2>Your Account details</h2></u>
            
            <form method="POST" action="account_details_update_confirm.jsp">
        <div class="container">
            <div class="row">
                <div class="col-left">
                    <label for="acc_id">Account id:</label>
                </div>
                <div class="col-right">
                    <input type="text" id="acc_id" name="acc_id" value="<%= acc_id %>" readonly="">
                </div>
            </div>
            <div class="row">
                <div class="col-left">
                    <label for="name">Name:</label>
                </div>
                <div class="col-right">
                    <input type="text" id="name" name="name" value="<%= name%>" required/>
                </div>
            </div>
            <div class="row">
                <div class="col-left">
                    <label for="dob">Date of Birth:</label>
                </div>
                <div class="col-right">
                    <input id="dob" type="date" name="dob" value="<%= dob%>" required/>
                </div>
            </div>
            <div class="row">
                <div class="col-left">
                    <label for="gender">Gender:</label>
                </div>
                <div class="col-right">
                    <input id="gender" type="radio" name="gender" value="male" required/>male
                    <input type="radio" name="gender" value="female" required=""/>female
                </div>
            </div>
            <div class="row">
                <div class="col-left">
                    <label for="phno">Phone Number:</label>
                </div>
                <div class="col-right">
                    <input id="phno" type="number" name="phno" max="9999999999" min="1000000000" value="<%= phno%>" required/>
                </div>
            </div>
            <div class="row">
                <div class="col-left">
                    <label for="email">Email id:</label>
                </div>
                <div class="col-right">
                    <input id="email" type="email" name="email" value="<%= email%>" required/>
                </div>
            </div>
            <div class="row">
                <div class="col-left">
                    <label for="password">Password:</label>
                </div>
                <div class="col-right">
                    <input id="password" type="password" name="password"required/>
                </div>
            </div>
            <div class="row">
                    <input type="submit" value="save"/>
            </div>
        </div>
            </form>  
    </center>
        <br/><br/>
    </body>
</html>
