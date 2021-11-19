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

/**
 *
 * @author student
 */
public class Delete_book_details extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
            int copies = 0,book_id=0;
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
                    book_id=Integer.parseInt(request.getParameter("book_id"));
                    Class.forName("com.mysql.jdbc.Driver");

                    co=1;

                    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectZ","root","rgukt123");
                    co=2;
                    ps=con.prepareStatement("select copies from book where book_id=?;");
                    ps.setInt(1,book_id);
                    co=3;
                    rs=ps.executeQuery();
                    co=4;
                    rs.next();
                        if(!rs.next())
                        {
                            copies=rs.getInt(1);
                            co=5;
                            ps=con.prepareStatement("select count(*) from avail_books where book_id=?;");
                            ps.setInt(1,book_id);
                            rs=ps.executeQuery();
                            rs.next();
                            int copies_avail=rs.getInt(1);
                            co=6;
                            if(copies_avail==copies)
                            {
                                co=7;
                                ps=con.prepareStatement("delete from total_books where book_id=?;");
                                ps.setInt(1, book_id);
                                ps.executeUpdate();

                                co=8;

                                ps=con.prepareStatement("delete from avail_books where book_id=?;");
                                ps.setInt(1, book_id);
                                ps.executeUpdate();

                                co=9;

                                ps=con.prepareStatement("delete from book where book_id=?;");
                                ps.setInt(1, book_id);
                                ps.executeUpdate();

                                co=10;

                                response.sendRedirect("books_deleted.html");
                            }
                            else
                            {
                                response.sendRedirect("deleting_error.html");
                            }
                            con.close();

                        } 
                }catch(Exception e){}
                
            }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Delete_book_details</title><style>\n" +
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
            
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
