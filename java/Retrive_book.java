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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author student
 */
@WebServlet(urlPatterns = {"/Retrive_book"})
public class Retrive_book extends HttpServlet {

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
        
        ResultSet rs=null;
        String name="",author="";
        int edition=0,copies=0;
        Connection con=null;
        int co=0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            co=1;
  
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectZ","root","rgukt123");
          co=2;
            /*PreparedStatement ps=con.prepareStatement("select * from book;");
            co=3;
            rs=ps.executeQuery();*/
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
            out.println("<title>Servlet Retrive_book</title><style>\n" +
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
"            \n" +
"            .container {\n" +
"                border-radius: 5px;\n" +
"                background-color: #f0f0f0;\n" +
"                padding: 20px;\n" +
"                \n" +
"              }\n" +
"\n" +
"              .col-left {\n" +
"                float: left;\n" +
"                width: 50%;\n" +
"                margin-top: 6px;\n" +
"                text-align: right;\n" +
"              }\n" +
"\n" +
"              .col-right {\n" +
"                float: left;\n" +
"                width: 50%;\n" +
"                margin-top: 6px;\n" +
"                text-align: left;\n" +
"              }\n" +
"\n" +
"              /* Clear floats after the columns */\n" +
"              .row:after {\n" +
"                content: \"\";\n" +
"                display: table;\n" +
"                clear: both;\n" +
"              }\n" +
"              \n" +
"              input[type=submit] {\n" +
"                background-color: #Books in Library4CAF50;\n" +
"                color: white;\n" +
"                padding: 12px 20px;\n" +
"                border: none;\n" +
"                border-radius: 4px;\n" +
"                cursor: pointer;\n" +
"                float: center;\n" +
"              }\n" +
"\n" +
"              input[type=submit]:hover {\n" +
"                background-color: #45a049;\n" +
"              }\n" +
                    
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
                    
"        #myInput {\n" +
"  background-position: 10px 10px;\n" +
"  background-repeat: no-repeat;\n" +
"  width: 60%;\n" +
"  font-size: 16px;\n" +
"  padding: 12px 20px 12px 40px;\n" +
"  border: 1px solid #ddd;\n" +
"  margin-bottom: 12px;\n" +
"}</style>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"header\">\n" +
"            <center><h1>Welcome to RGUKT RK Valley Library</h1></center>\n" +
"        </div>\n" +
"        <br/><br/>");
            
            out.println("<center><input style=\"background-color:LightGray; color:Black; border-color:Black\" type=\"text\" id=\"myInput\" onkeyup=\"myFunction()\" placeholder=\"Search for book\" /></center>");
            out.println("<script>function myFunction(){// Declare variables \n" +
"  var input, filter, table, tr, td, i;\n" +
"  input = document.getElementById(\"myInput\");\n" +
"  filter = input.value.toUpperCase();\n" +
"  table = document.getElementById(\"myTable\");\n" +
"  tr = table.getElementsByTagName(\"tr\");\n" +
"\n" +
"  // Loop through all table rows, and hide those who don't match the search query\n" +
"  for (i = 0; i < tr.length; i++) {\n" +
"    td = tr[i].getElementsByTagName(\"td\") ; \n" +
"    for(j=0 ; j<td.length ; j++)\n" +
"    {\n" +
"      let tdata = td[j] ;\n" +
"      if (tdata) {\n" +
"        if (tdata.innerHTML.toUpperCase().indexOf(filter) > -1) {\n" +
"          tr[i].style.display = \"\";\n" +
"          break ; \n" +
"        } else {\n" +
"          tr[i].style.display = \"none\";\n" +
"        }\n" +
"      } \n" +
"    }\n" +
"  }}</script>");
            
            
            out.println("<h2><center><u>Books in Library</u></center></h2>");
            
            int rowCount = 0;
            
            out.println("<center><table id=\"myTable\" title=\"books\" BORDER=1>");
            ResultSetMetaData rsmd;
            try {
                rsmd = rs.getMetaData();

                int columnCount = rsmd.getColumnCount();
                // table header
                
                out.println("<tr>");
                for (int i = 0; i < columnCount; i++) {
                      out.println("<th>" + rsmd.getColumnLabel(i + 1) + "</th>");
                    }
                out.println("</tr>");
                // the data 
                while (rs.next()) {
                     out.println("<tr title=\""+rs.getString("book_name")+"\">");
                     for (int i = 0; i < columnCount; i++) {
                       out.println("<td>" + rs.getString(i + 1) + "</td>");
                       }
                    out.println("</tr>");
                    
                }
                out.println("</table></center>");
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Retrive_book.class.getName()).log(Level.SEVERE, null, ex);
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
