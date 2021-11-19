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
public class Accept_submission_confirm extends HttpServlet {

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
        
        int isd_id=Integer.parseInt(request.getParameter("isd_id"));
        int book_id=0,book_id_offset=0;
        int co=0;
        
        HttpSession session=request.getSession();
        String acc_id_top_left= (String) session.getAttribute("acc_id_top_left");
        if(acc_id_top_left==null || !(acc_id_top_left.substring(0, 2).equals("cl")))
            {
                response.sendRedirect("login.jsp");
            }
        else
        {
            ResultSet rs=null;
            Connection con=null;
            PreparedStatement ps=null;

            try{
                co=1;
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectZ","root","rgukt123");
                co=2;
                ps=con.prepareStatement("select * from issued where isd_id=?;");
                ps.setInt(1, isd_id);
                rs=ps.executeQuery();
                if(rs.next())
                {
                    book_id=rs.getInt("book_id");
                    book_id_offset=rs.getInt("book_id_offset");
                    String acc_id=rs.getString("acc_id");
                    String req_date_time=rs.getString("req_date_time");
                    String isd_date_time=rs.getString("isd_date_time");
                    co=3;
                    String acc_type=acc_id.substring(1, 3);
                            ps=con.prepareStatement("select * from issue_rules where acc_type=?;");
                            ps.setString(1,acc_type);
                            rs=ps.executeQuery();
                            rs.next();
                            int duration=rs.getInt("duration");

                            co=4;

                            ps=con.prepareStatement("select TIME_TO_sec(timediff(now(),isd_date_time)) from issued where isd_id=? and TIME_TO_sec(timediff(now(),isd_date_time))>?;");
                            ps.setInt(1,isd_id);
                            ps.setInt(2,duration);
                            rs=ps.executeQuery();

                    co=5;
                    if(rs.next())//checking if time exceeded or not
                    {
                        int time=rs.getInt(1);
                        co=6;
                        ps=con.prepareStatement("select ifnull(max(penalty_id),0) from penalty;");
                                        rs=ps.executeQuery();
                                        rs.next();
                                        int penalty_id=rs.getInt(1);
                                        penalty_id=penalty_id+1;
                        co=7;                
                        ps=con.prepareStatement("insert into penalty values(?,?,?,?,?,?,now(),?);");
                        ps.setInt(1,penalty_id);
                        ps.setString(2, acc_id);
                        ps.setInt(3,book_id);
                        ps.setInt(4,book_id_offset);
                        ps.setString(5, req_date_time);
                        ps.setString(6, isd_date_time);
                        ps.setInt(7,time);
                        ps.executeUpdate();
                    }


                    ps=con.prepareStatement("delete from issued where isd_id=?;");
                    ps.setInt(1, isd_id);
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
                response.sendRedirect("Accept_submission");
            }catch(Exception e){}
        
        }
        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Accept_submission_confirm</title><style>\n" +
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
            out.println("<h1>Servlet Accept_submission_confirm at " + request.getContextPath() + "</h1>");
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
