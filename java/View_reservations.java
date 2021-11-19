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
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class View_reservations extends HttpServlet {

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
        ResultSet rs=null;
        Connection con=null;
        PreparedStatement ps=null;
        int co=0;
        
        HttpSession session=request.getSession();
        String acc_id_top_left= (String) session.getAttribute("acc_id_top_left");
        if(acc_id_top_left==null || !(acc_id_top_left.substring(0,1).equals("u")))
            {
                response.sendRedirect("login.jsp");
            }
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            co=1;
  
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectZ","root","rgukt123");
            co=2;
           
            ps=con.prepareStatement("select * from reserve where acc_id=?;");
            ps.setString(1, acc_id_top_left);
            rs=ps.executeQuery();
            co=4;
            
        }catch(Exception e){}
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet View_requests</title><style>\n" +
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
                               "input[type=submit] {\n" +
            "  background-color: #4CAF50;\n" +
            "  color: white;\n" +
            "  padding: 12px 20px;\n" +
            "  border: none;\n" +
            "  border-radius: 4px;\n" +
            "  cursor: pointer;\n" +
            "}"+
                       "input[type=submit]:hover {\n" +
            "  background-color: #45a049;\n" +
            "}" +

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
            int rowCount = 0;
            
            out.println("<center><P ALIGN='center'><TABLE BORDER=1>");
            ResultSetMetaData rsmd;
            try {
                rsmd = rs.getMetaData();

                int columnCount = rsmd.getColumnCount();
                // table header
                
                if(rs.next())
                {
                    out.println("<TR>");
                    for (int i = 0; i < columnCount; i++) {
                          out.println("<TH>" + rsmd.getColumnLabel(i + 1) + "</TH>");
                        }
                    out.println("<TH>select to cancel</TH>");
                    out.println("</TR>");
                
                // the data 
                    out.println("<form  method=\"POST\" action=\"Reserve_cancel\" name=\"cancel\">");
                    do {
                         out.println("<TR>");
                         for (int i = 0; i < columnCount; i++) {
                           out.println("<TD>" + rs.getString(i + 1) + "</TD>");
                           }
                        out.println("<TD><input type=\"radio\" name=\"res_id\" value="+rs.getInt("res_id")+" checked></TD>");
                        out.println("</TR>");

                    }while (rs.next());
                    out.println("<center><input type=\"submit\" value=\"click here to cancel selected reservation\"></form>");
                }
                else
                    out.println("No reservations");
                out.println("</TABLE></P></center>");
                
                con.close();
            } catch (SQLException ex) {
                
            }
            
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
