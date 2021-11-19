<%-- 
    Document   : add_book_form
    Created on : 6 Jun, 2020, 1:06:02 PM
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
            String acc_id_top_left=(String) session.getAttribute("acc_id_top_left");
            if(acc_id_top_left==null || !(acc_id_top_left.substring(0, 2).equals("ad")))
            {
                response.sendRedirect("login.jsp");
            }
        %>
        <div class="header">
            <p id="acc_id"><%=acc_id_top_left%></p>
            <center><h2><u>RGUKT RK Valley Library Management System</u></h2></center>
        </div>
            <div class="topnav">
                <nav>
                    <a href="admin_account_details.jsp">Account</a>
                    <a href="Retrive_book" target="_blank">View all books</a>
                    <a href="add_book_form.jsp" >Add book</a>
                    <a href="Periodic_check">Periodic check</a>
                    <a href="View_account_details">View all accounts</a>
                    <a href="logout_process.jsp" target="_top" style="float:right;">Logout</a>
                </nav>
        </div>
        
    <center>
        <h3>Add new book to Database</h3>
        <form  method="POST" action="Add_book_details" name="bookdetails">
            Book name:<input type="text" name="name" required="" maxlength="40"/><br/>
            Book author:<input type="text" name="author" required="" maxlength="30"/><br/>
            Book edition:<input type="number" name="edition" required=""/><br/>
            Book copies:<input type="number" name="copies" min="0" max="10" required=""/><br/>
            <input type="submit" value="Add Books to Database"/>
        </form>
        <br/><br/>
        
        <h3>Add books of existing type to database</h3>
        <form  method="POST" action="Add_books_temp" name="books" >
            Book Isbn:<input type="number" name="book_id" min="1" required=""/><br/>
            Book copies:<input type="number" name="copies" min="0" required=""/><br/>
            <input type="submit" value="Add Books to Database"/>
        </form>
        
    </center>
    </body>
</html>
