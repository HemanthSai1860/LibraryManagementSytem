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
public class Issue_book_confirm extends HttpServlet {

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
        String acc_id="",req_date_time="";
        int co=0;
        
        HttpSession session=request.getSession();
        String acc_id_top_left= (String) session.getAttribute("acc_id_top_left");
        if(acc_id_top_left==null || !(acc_id_top_left.substring(0, 2).equals("cl")))
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
                    acc_id=rs.getString("acc_id");
                    book_id=rs.getInt("book_id");
                    book_id_offset=rs.getInt("book_id_offset");
                    req_date_time=rs.getString("req_date_time");

                    co=3;

                    ps=con.prepareStatement("select ifnull(max(isd_id),0) from issued;");
                    rs=ps.executeQuery();
                    rs.next();
                    int isd_id=rs.getInt(1);
                    isd_id=isd_id+1;

                    co=4;

                    ps=con.prepareStatement("insert into issued values(?,?,?,?,?,now());");
                    ps.setInt(1,isd_id);
                    ps.setString(2,acc_id);
                    ps.setInt(3,book_id);
                    ps.setInt(4,book_id_offset);
                    ps.setString(5,req_date_time);
                    ps.executeUpdate();

                    co=5;

                    ps=con.prepareStatement("delete from request where req_id=?;");
                    ps.setInt(1, req_id1);
                    ps.executeUpdate();

                    ps=con.prepareStatement("update book set used=used+1,latest_used=now() where book_id=?;");
                    ps.setInt(1, book_id);
                    ps.executeUpdate();
                }


                co=6;




                con.close();
                co=10;
                response.sendRedirect("Issue_book");
            }catch(Exception e){}
            
        }

        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Issue_book_confirm</title><style>\n" +
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
"\n" +
"        <div class=\"topnav\">\n" +
"            <center> \n" +
"                <nav>\n" +
"                    <a href=\"clerk_account_details.jsp\">Account</a>\n" +
"                    <a href=\"Retrive_book\" target=\"_blank\">View all books</a>\n" +
"                    <a href=\"Issue_book\">Issue book</a>\n" +
"                    <a href=\"Accept_submission\" >Accept submission</a>\n" +
"                    <a href=\"Penalty_paid\" >Penalty paid</a>\n" +
"                    <a href=\"logout_process.jsp\" target=\"_top\" style=\"float: right;\">Logout</a>\n" +
"                </nav>\n" +
"            </center>\n" +
"        </div>");
            out.println("<h1>issued"+co+"</h1>");
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
