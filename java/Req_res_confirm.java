/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author student
 */
@WebServlet(urlPatterns = {"/Req_res_confirm"})
public class Req_res_confirm extends HttpServlet {

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
        int book_id=Integer.parseInt(request.getParameter("book_id"));
        String acc_id=request.getParameter("acc_id");
       
        
        ResultSet rs=null;
        Connection con=null;
        PreparedStatement ps=null;
        int co=0;
        
        HttpSession session=request.getSession();
        String acc_id_top_left= (String) session.getAttribute("acc_id_top_left");
        if(acc_id_top_left==null || !(acc_id_top_left.substring(0,1).equals("u")) || !(acc_id.equals(acc_id_top_left)))
            {
                response.sendRedirect("login.jsp");
            }
        else{
         try{
                Class.forName("com.mysql.jdbc.Driver");

                co=1;

                int max_books_temp=0;
                
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectZ","root","rgukt123");
                co=2;
                
                ps=con.prepareStatement("select count(*) from penalty where acc_id=?;");
                ps.setString(1,acc_id);
                rs=ps.executeQuery();
                rs.next();
                
                if(rs.getInt(1)==0)//checking whether penalty is there or not in penalty table
                {
                    String acc_type=acc_id.substring(1, 3);
                    ps=con.prepareStatement("select * from issue_rules where acc_type=?;");
                    ps.setString(1,acc_type);
                    rs=ps.executeQuery();
                    rs.next();
                    int max_books=rs.getInt("max_books");
                    int duration=rs.getInt("duration");
                    
                    ps=con.prepareStatement("select count(*) from issued where acc_id=? and TIME_TO_sec(timediff(now(),isd_date_time))>?;");
                    ps.setString(1,acc_id);
                    ps.setInt(2,duration);
                    rs=ps.executeQuery();
                    rs.next();
                    if(rs.getInt(1)==0)//checking whether time limit exceeded or not in any of books of issued table
                    {
                        
                        ps=con.prepareStatement("select count(*) from request where acc_id=?;");
                        ps.setString(1,acc_id);
                        rs=ps.executeQuery();
                        rs.next();
                        max_books_temp=max_books_temp+rs.getInt(1);
                        
                        ps=con.prepareStatement("select count(*) from issued where acc_id=?;");
                        ps.setString(1,acc_id);
                        rs=ps.executeQuery();
                        rs.next();
                        max_books_temp=max_books_temp+rs.getInt(1);
                        
                        ps=con.prepareStatement("select count(*) from reserve where acc_id=?;");
                        ps.setString(1,acc_id);
                        rs=ps.executeQuery();
                        rs.next();
                        max_books_temp=max_books_temp+rs.getInt(1);
                        
                        if(max_books_temp<max_books)//checking whether he can req/res one more book or his limit exceeded
                        {
                            ps=con.prepareStatement("select book_id_offset from avail_books where book_id=? limit 1;");
                            co=3;
                            ps.setInt(1,book_id);
                            rs=ps.executeQuery();

                            if(rs.next())
                            {
                                int book_id_offset=rs.getInt(1);
                                ps=con.prepareStatement("select ifnull(max(req_id),0) from request;");
                                rs=ps.executeQuery();
                                rs.next();
                                int req_id=rs.getInt(1);
                                req_id=req_id+1;
                                ps=con.prepareStatement("insert into request values(?,?,?,?,now());");
                                ps.setInt(1,req_id);
                                ps.setString(2,acc_id);
                                ps.setInt(3,book_id);
                                ps.setInt(4,book_id_offset);
                                ps.executeUpdate();
                                ps=con.prepareStatement("delete from avail_books where book_id=? and book_id_offset=?;");
                                ps.setInt(1,book_id);
                                ps.setInt(2,book_id_offset);
                                ps.executeUpdate();

                                response.sendRedirect("book_requested.jsp");
                            }
                            else{
                                ps=con.prepareStatement("select ifnull(max(res_id),0) from reserve;");
                                rs=ps.executeQuery();
                                rs.next();
                                int res_id=rs.getInt(1);
                                res_id=res_id+1;
                                ps=con.prepareStatement("insert into reserve values(?,?,?,now());");
                                ps.setInt(1,res_id);
                                ps.setString(2,acc_id);
                                ps.setInt(3,book_id);
                                ps.executeUpdate();

                                response.sendRedirect("book_reserved.jsp");
                            }
                        }
                    }
                }
                con.close();
            }
            catch(Exception e){}
        
        }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Req_res_confirm_servlet</title><style>\n" +
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
"        <div class=\"topnav\">\n" +
"            <center> \n" +
"                <nav>\n" +
"                <a href=\"user_account_details.jsp\" >Account</a>\n" +
"                <a href=\"Your_books\" >your books</a>\n" +
"                <a href=\"Retrive_book\" target=\"_blank\" >view all books</a>\n" +
"                <a href=\"Req_res\" >Request/Reserve</a>\n" +
"                <a href=\"View_requests\">view requests</a>\n" +
"                <a href=\"View_reservations\">view reservations</a>\n" +
"                <a href=\"logout_process.jsp\"  style=\"float: right;\" target=\"_top\">Logout</a>\n" +
"                </nav>\n" +
"            </center>\n" +
"        </div>");
           
            out.println("<h3>sorry your limit exceeded or penalty is not paid");
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
