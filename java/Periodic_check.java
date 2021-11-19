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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author student
 */
public class Periodic_check extends HttpServlet {

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
                    Class.forName("com.mysql.jdbc.Driver");

                    co=1;

                    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectZ","root","rgukt123");
                    co=2;

                    ps=con.prepareStatement("select * from book where used=0 and TIME_TO_sec(timediff(now(),latest_used))>?;");
                    
                    ps.setInt(1, 1500);
                    //ps.setInt(1, 1000);
                    rs=ps.executeQuery();
                    co=4;

                }catch(Exception e){}
            } 
            
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Periodic_check</title><style>\n" +
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
                                        
                    "table {\n" +
"  border-collapse: collapse;\n" +
"  width: 100%;\n" +
"}\n" +
"\n" +
"th, td {\n" +
"  padding: 8px;\n" +
"  text-align: left;\n" +
"  border-bottom: 1px solid #ddd;\n" +
"}\n" +
"\n" +
"tr:hover {background-color:#f5f5f5;}"+
                    
                    
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
            
            
            
            out.println("<center><P ALIGN='center'><TABLE BORDER=1>");
            ResultSetMetaData rsmd;
            try {
                rsmd = rs.getMetaData();

                int columnCount = rsmd.getColumnCount();
                // table header
                if(rs.next())
                {
                    out.println("<h3>Books which are not used from the past 5 years</h3>");
                    out.println("<TR>");
                    
                    for (int i = 0; i < columnCount; i++) {
                          out.println("<TH>" + rsmd.getColumnLabel(i + 1) + "</TH>");
                    }
                    out.println("<TH>select to delete book</TH>");
                    out.println("</TR>");
                out.println("<form  method=\"POST\" action=\"Delete_book_details\" name=\"accept\">");
                // the data 
                    do {
                         out.println("<TR>");
                         for (int i = 0; i < columnCount; i++) {
                           out.println("<TD>" + rs.getString(i + 1) + "</TD>");
                           }
                         out.println("<TD><input type=\"radio\" name=\"book_id\" value="+rs.getInt("book_id")+" checked></TD>");
                        out.println("</TR>");

                    }while (rs.next());
                    out.println("<center><input type=\"submit\" value=\"click here to delete selected book\"></form>");
                }
                else
                    out.println("No books which are not used from the past 5 years");
                out.println("</TABLE></P></center>");
                
                con.close();
            } catch (SQLException ex) {
                
            }
            
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
