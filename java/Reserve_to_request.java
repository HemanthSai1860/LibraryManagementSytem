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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author student
 */
@WebServlet(urlPatterns = {"/Reserve_to_request"})
public class Reserve_to_request extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
                int book_id;
                String book_id_str = (String)request.getAttribute("book_id");
                book_id=Integer.parseInt(book_id_str);
                int book_id_offset;
                String book_id_offset_str=(String)request.getAttribute("book_id_offset");
                book_id_offset = Integer.parseInt(book_id_offset_str);
        
        
        HttpSession session=request.getSession();
        String acc_id_top_left= (String) session.getAttribute("acc_id_top_left");
        if(acc_id_top_left==null)
            {
                response.sendRedirect("login.jsp");
            }
        else{
            ResultSet rs=null;
            Connection con=null;
            PreparedStatement ps=null;

                int c=0;
                try{


                    c=1;
                    Class.forName("com.mysql.jdbc.Driver");
                    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectZ","root","rgukt123");
                    ps=con.prepareStatement("select * from reserve where book_id=? limit 1;");
                    ps.setInt(1, book_id);
                    rs=ps.executeQuery();
                    c=2;
                    if(rs.next())
                    {
                        int res_id=rs.getInt("res_id");
                        String acc_id=rs.getString("acc_id");
                        c=3;
                        ps=con.prepareStatement("select ifnull(max(req_id),0) from request;");
                        rs=ps.executeQuery();
                        rs.next();
                        int req_id=rs.getInt(1)+1;
                        c=4;
                        ps=con.prepareStatement("insert into request values(?,?,?,?,now());");
                        ps.setInt(1, req_id);
                        ps.setString(2,acc_id);
                        ps.setInt(3, book_id);
                        ps.setInt(4, book_id_offset);
                        ps.executeUpdate();
                        c=5;
                        ps=con.prepareStatement("delete from reserve where res_id=?;");
                        ps.setInt(1, res_id);
                        ps.executeUpdate();
                        c=6;
                    }
                    else
                    {
                        ps=con.prepareStatement("insert into avail_books values(?,?)");
                        ps.setInt(1, book_id);
                        ps.setInt(2, book_id_offset);
                        ps.executeUpdate();
                        c=10;
                    }
                    con.close();
                    c=11;

                }catch(Exception e){}

        }
    }

}
