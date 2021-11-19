/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author student
 */
public class Temp extends HttpServlet {

    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
        
        String book_id=request.getParameter("book_id");
        String copies=request.getParameter("copies");
        request.setAttribute("book_id", book_id);
        request.setAttribute("copies", copies);
        RequestDispatcher rd=request.getRequestDispatcher("Add_books");
        rd.forward(request, response);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Temp</title><style>\n" +
"            body {\n" +
"              margin: 0;\n" +
"            }\n" +
"\n" +
"            /* Style the header */\n" +
"            .header {\n" +
"              padding: 10px;\n" +
"              background: skyblue;\n" +
"              color: black;\n" +
"              font-size: 20px;\n" +
"            }\n" +
"\n" +
"            /* Style the top navigation bar */\n" +
"            .topnav {\n" +
"              overflow: hidden;\n" +
"              background-color: #333;\n" +
"            }\n" +
"\n" +
"            /* Style the topnav links */\n" +
"            .topnav a {\n" +
"              float: left;\n" +
"              display: block;\n" +
"              color: #f2f2f2;\n" +
"              text-align: center;\n" +
"              padding: 14px 16px;\n" +
"              text-decoration: none;\n" +
"              font-size: 17px;\n" +
"            }\n" +
"\n" +
"            /* Change color on hover */\n" +
"            .topnav a:hover {\n" +
"              background-color: #ddd;\n" +
"              color: black;\n" +
"              font-size: 17px;\n" +
"            }\n" +
"\n" +
"\n" +
"            /* Footer */\n" +
"            .footer {\n" +
"              padding: 20px;\n" +
"              text-align: center;\n" +
"              background: #ddd;\n" +
"              float: top;\n" +
"            }\n" +
"        </style>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div>\n" +
    "            <p id=\"acc_id\">acc_id</p>\n" +
    "            <a href=\"logout_process.jsp\"  style=\"float: right;\" target=\"_top\">Logout</a>\n" +
    "            <center><h2><u>RGUKT RK Valley Library Management System</u></h2></center>\n" +
    "        </div>\n" +
    "        \n" +
    "        <hr>\n" +
    "        \n" +
    "        <div>\n" +
    "            <center> \n" +
    "                <nav>\n" +
    "                <a href=\"Retrive_book\" >view all books</a>|\n" +
    "                <a href=\"add_book_form.jsp\" >add book</a>|" +
    "                <a href=\"delete_book_form.jsp\" >delete book</a>" +
    "                </nav>\n" +
    "            </center>\n" +
    "        </div>");
            out.println("<h1>" + book_id + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        
    }

   

}
