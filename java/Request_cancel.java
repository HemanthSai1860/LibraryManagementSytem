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
import java.sql.Statement;
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
@WebServlet(urlPatterns = {"/Request_cancel"})
public class Request_cancel extends HttpServlet {

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
        int req_id1=Integer.parseInt(request.getParameter("req_id"));
        int book_id=0,book_id_offset=0;
        int co=0;
        
        HttpSession session=request.getSession();
        String acc_id_top_left= (String) session.getAttribute("acc_id_top_left");
        if(acc_id_top_left==null || !(acc_id_top_left.substring(0,1).equals("u")))
            {
                response.sendRedirect("login.jsp");
            }
        else{
            ResultSet rs=null;
            Connection con=null;
            PreparedStatement ps=null;

            try{
                co=1;
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectZ","root","rgukt123");
                co=2;
                ps=con.prepareStatement("select * from request where req_id=?;");
                ps.setInt(1, req_id1);
                rs=ps.executeQuery();
                if(rs.next())
                {
                    book_id=rs.getInt("book_id");
                    book_id_offset=rs.getInt("book_id_offset");
                    String acc_id=rs.getString("acc_id");
                    co=3;

                    if(!(acc_id.equals(acc_id_top_left)))
                        response.sendRedirect("login.jsp");
                    else{
                        ps=con.prepareStatement("delete from request where req_id=?;");
                        ps.setInt(1, req_id1);
                        ps.executeUpdate();
                        co=4;




                        con.close();
                        co=10;
                        String book_id_str=String.valueOf(book_id);
                        String book_id_offset_str=String.valueOf(book_id_offset);
                        request.setAttribute("book_id", book_id_str);
                        request.setAttribute("book_id_offset", book_id_offset_str);
                        RequestDispatcher rd=request.getRequestDispatcher("Reserve_to_request");
                        rd.include(request, response);
                    }
                }
                response.sendRedirect("View_requests");
            }catch(Exception e){}
            
        }

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Request_cancel</title><style>\n" +
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
            
            out.println("<h3> request is cancelled "+co+"</h3>");
            
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
