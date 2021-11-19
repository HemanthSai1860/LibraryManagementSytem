/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Add_book_details extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
            String name = null;
            String author = null;
            int edt = 0,i=0;
            int copies = 0;
            ResultSet rs=null;
            Connection con=null;
            PreparedStatement ps;
            int co=0;
            
            HttpSession session=request.getSession();
            String acc_id_top_left= (String) session.getAttribute("acc_id_top_left");
            if(acc_id_top_left==null || !(acc_id_top_left.substring(0, 2).equals("ad")))
            {
                response.sendRedirect("login.jsp");
            }
            else{
                try{
                    name=request.getParameter("name");
                    author=request.getParameter("author");
                    edt=Integer.parseInt(request.getParameter("edition"));
                    copies=Integer.parseInt(request.getParameter("copies"));
                    Class.forName("com.mysql.jdbc.Driver");

                    co=1;

                    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectZ","root","rgukt123");
                    co=2;
                    ps=con.prepareStatement("select count(*) from book where book_name=? and book_author=? and book_edition=?;");
                    co=3;
                    ps.setString(1, name);
                    ps.setString(2, author);
                    ps.setInt(3, edt);
                    rs=ps.executeQuery();
                    co=4;
                    rs.next();
                        if(rs.getInt(1)!=0)
                        {
                            RequestDispatcher rd=request.getRequestDispatcher("book_exists.html");
                            rd.forward(request, response);
                        }
                        else{
                            ps=con.prepareStatement("select ifnull(max(book_id),0) from book;");
                            rs=ps.executeQuery();
                            rs.next();
                            i=rs.getInt(1);
                            i=i+1;
                            ps=con.prepareStatement("insert into book values(?,?,?,?,0,0);");
                            ps.setInt(1, i);
                            ps.setString(2, name);
                            ps.setString(3, author);
                            ps.setInt(4,edt);
                            ps.executeUpdate();
                            con.close();
                            String is=String.valueOf(i);
                            String cs=String.valueOf(copies);
                            request.setAttribute("book_id", is);
                            request.setAttribute("copies", cs);
                            co=5;
                            if(true)
                            {RequestDispatcher rd=request.getRequestDispatcher("Add_books");
                            co=6;
                            rd.forward(request, response);}
                            co=7;
                        }
                }catch(Exception e){}
                
            }

            try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Add_books</title><style>\n" +
"            body {\n" +
"              margin: 0;\n" +
"            }\n" +
"\n" +
"            /* Style the header */\n" +
"            .header {\n" +
"              padding: 10px;\n" +
"              background: skyblue;\n" +
"              color: black;\n" +
"              font-size: 17px;\n" +
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
            out.println("<div class=\"header\">\n" +
"            <p id=\"acc_id\">"+acc_id_top_left+"</p>\n" +
"            <center><h2><u>RGUKT RK Valley Library Management System</u></h2></center>\n" +
"        </div>\n" +
"            <div class=\"topnav\">\n" +
"                <nav>\n" +
"                    <a href=\"admin_account_details.jsp\">Account</a>\n" +
"                    <a href=\"Retrive_book\" target=\"_blank\">View all books</a>\n" +
"                    <a href=\"add_book_form.jsp\" >Add book</a>\n" +
"                    <a href=\"Periodic_check\">Periodic check</a>\n" +
"                    <a href=\"View_account_details\">View all accounts</a>\n" +
"                    <a href=\"logout_process.jsp\" target=\"_top\" style=\"float:right;\">Logout</a>\n" +
"                </nav>\n" +
"        </div>");
            out.println("<h4><a href=\"add_book_form.html\">go back</a>"+co+"</h4>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
