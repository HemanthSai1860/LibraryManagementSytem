/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
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

public class Add_books extends HttpServlet {
    

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
        ResultSet rs=null,rs1=null;
        Connection con=null;
        PreparedStatement ps,ps1;
        int co=0;
        String copies_str=(String)request.getAttribute("copies");
        int copies=Integer.parseInt(copies_str);
        String book_id_str=(String)request.getAttribute("book_id");
        int book_id=Integer.parseInt(book_id_str);
        
        HttpSession session=request.getSession();
        String acc_id_top_left= (String) session.getAttribute("acc_id_top_left");
        if(acc_id_top_left==null || !(acc_id_top_left.substring(0, 2).equals("ad")))
            {
                response.sendRedirect("login.jsp");
            }
        else{
            try{
                Class.forName("com.mysql.jdbc.Driver");

                co=1;

                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectZ","root","rgukt123");
                co=2;
                ps=con.prepareStatement("select copies from book where book_id=?;");
                co=3;
                ps.setInt(1,book_id);

                rs=ps.executeQuery();
                co=4;


                if(rs.next())
                {
                    int count=rs.getInt(1);
                    ps1=con.prepareStatement("select ifnull(max(book_id_offset),0) from total_books where book_id=?;");
                    ps1.setInt(1,book_id);
                    rs1=ps1.executeQuery();
                    rs1.next();
                    co=5;
                    int c=rs1.getInt(1);
                    count=copies+count;
                    c=c+1;
                    ps=con.prepareStatement("insert into total_books values(?,?);");
                    ps1=con.prepareStatement("insert into avail_books values(?,?);");
                    String s="";
                    co=6;
                    while(copies!=0)
                    {
                        ps.setInt(1, book_id);
                        ps.setInt(2, c);
                        ps1.setInt(1, book_id);
                        ps1.setInt(2, c);
                        ps.executeUpdate();
                        ps1.executeUpdate();
                        c=c+1;
                        copies=copies-1;
                    }

                    co=7;
                    ps=con.prepareStatement("UPDATE book set copies=? where book_id=?;");
                    ps.setInt(1,count);
                    ps.setInt(2,book_id);
                    ps.executeUpdate();
                    co=8;
                    response.sendRedirect("books_added.jsp");
                    co=10;
                    con.close();
                    co=11;
                }

            }catch(Exception e){}
            
        }
        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Add_books</title>");            
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
            out.println("<h2>book id doesnot exist in database </h2>");
            out.println("<h4><a href=\"add_book_form.html\">go back</a></h4>");
            out.println("</body>");
            out.println("</html>");
        }
    }


}
