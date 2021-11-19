<%-- 
    Document   : register
    Created on : 7 Jun, 2020, 2:13:02 PM
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
              
              input[type=text],input[type=number],input[type=email],input[type=password],input[type=date], select, textarea {
                  width: 25%;
                  padding: 8px;
                  border: 1px solid black;
                  border-radius: 4px;
                  resize: vertical;
                  background-color: lightgoldenrodyellow;
                  
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
        <div class="header">
            <center><h1>Welcome to RGUKT RK Valley Library</h1></center>
        </div>
        <br/>
        <div class="container">
            <center><h3><u>Registration Form</u></h3></center>
            <center>
            <form name="hello" action="register_process.jsp" method="POST">
                Enter name(this is used as your id):<br/><input type="text" name="name" required/><br/><br/>
                Enter dob(YYYY-MM-DD):<br/><input type="date" name="dob" value="2000-05-27" required/><br/><br/>
                Select gender:<br/><input type="radio" name="gender" value="male" required/>male
                <input type="radio" name="gender" value="female"/>female<br/><br/>
                Enter ph.no:<br/><input type="number" name="phno" max="9999999999" min="1000000000" required/><br/><br/>
                Select type:<br/>
                <select name="type">
                    <option value="uug">Under Graduate(uug)</option>
                    <option value="upg">Post Graduate(upg)</option>
                    <option value="urs">Research Scholar(urs)</option>
                    <option value="ufa">Faculty Member(ufa)</option>
                    <option value="cl">Clerk(cl)</option>
                    <option value="ad">Admin(ad)</option>
                </select><br/><br/>
                Enter email:<br/><input type="email" name="email" required/><br/><br/>
                Enter password:<br/><input type="password" minlength="6" maxlength="15" name="password" required/><br/><br/>
                <p>(Remember to use username as type+name at login.  eg:uuglokesh)</p>
                <input type="submit" value="Register"/>
                <br/>
            </form>
            </center>
        </div>
        <center>
            <a href="login.jsp">Already a user,chick here to login</a>
        </center>
        <br/><br/><br/><br/>
    </body>
</html>
