/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(urlPatterns = {"/Req_res"})
public class Req_res extends HttpServlet {

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
            
            Statement st=con.createStatement();
            String a="select*from book;";
            rs=st.executeQuery(a);
            co=4;
        }catch(Exception e){}
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Req_res</title><style>\n" +
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
                    "input[type=text], select, textarea {\n" +
"  width: 50%;\n" +
"  padding: 12px;\n" +
"  border: 1px solid #ccc;\n" +
"  border-radius: 4px;\n" +
"  resize: vertical;\n" +
"}"+
                    
                    "* {\n" +
"  box-sizing: border-box;\n" +
"}\n" +
"\n" +
              
"\n" +
"label {\n" +
"  padding: 12px 12px 12px 0;\n" +
"  display: inline-block;\n" +
"}\n" +
"\n" +
                    
"input[type=submit] {\n" +
"  background-color: #4CAF50;\n" +
"  color: white;\n" +
"  padding: 12px 20px;\n" +
"  border: none;\n" +
"  border-radius: 4px;\n" +
"  cursor: pointer;\n" +
"  float: center;\n" +
"}\n" +
"\n" +
                    
"input[type=submit]:hover {\n" +
"  background-color: #45a049;\n" +
"}\n" +
"\n" +
                    
".container {\n" +
"  border-radius: 5px;\n" +
"  background-color: #f5f5f5;\n" +
"  padding: 20px;\n" +
"}\n" +
"\n" +
                    
".col-25 {\n" +
"  float: left;\n" +
"  width: 40%;\n" +
"  margin-top: 6px;\n" +
"  text-align: right\n" +
"}\n" +
"\n" +
                    
".col-75 {\n" +
"  float: left;\n" +
"  width: 60%;\n" +
"  margin-top: 6px;\n" +
"  text-align: left;\n" +
"}\n" +
"\n" +
                    
"/* Clear floats after the columns */\n" +
".row:after {\n" +
"  content: \"\";\n" +
"  display: table;\n" +
"  clear: both;\n" +
"}"+

                    "select { \n" +
"                appearance: none; \n" +
"                outline: 0; \n" +
"                background: lightgray; \n" +
"                background-image: none; \n" +
"                color: black; \n" +
"                cursor: pointer; \n" +
"                border:1px solid black; \n" +
"                border-radius:3px; \n" +
"            } \n" +
                    
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
            out.println("<div class=\"cont\">");
            out.println("<center><h2><u>Request/Reserve</u></h2></center>");
            out.println("<center><h3>Edit below form</h3><center>");
           
            try {
                
                
                
                out.println("<div class=\"container\">\n" +
"  <form name=\"request_form\" method=\"POST\" action=\"Req_res_confirm\">\n" +
"  <div class=\"row\">\n" +
"    <div class=\"col-25\">\n" +
"      <label for=\"acc_id\">Acc_id:</label>\n" +
"    </div>\n" +
"    <div class=\"col-75\">\n" +
"      <input type=\"text\" id=\"acc_id\" name=\"acc_id\" value=\""+acc_id_top_left+"\" readonly>\n" +
"    </div>\n" +
"  </div>\n" +
"  <div class=\"row\">\n" +
"    <div class=\"col-25\">\n" +
"      <label for=\"select\">Select book:</label>\n" +
"    </div>\n" +
"    <div class=\"col-75\">\n" +
"      <select id=\"select\" name=\"book_id\">\n");
                
        while(rs.next())
                    out.println("<option value="+rs.getInt("book_id")+">"+rs.getString("book_name")+" : "+rs.getString("book_author")+" : "+rs.getInt("book_edition")+"</option>");
out.println("      </select>\n" +
"    </div>\n" +
"  </div>\n" +
"  <div class=\"row\">\n" +
"    <input  type=\"submit\" value=\"Submit\">\n" +
"  </div>\n" +
"  </form>\n" +
"</div>");
                
                
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Retrive_book.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("</div>");
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
