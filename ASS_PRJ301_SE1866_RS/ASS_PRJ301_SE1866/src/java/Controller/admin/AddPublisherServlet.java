/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.admin;

import Dal.PublisherDAO;
import Model.Publisher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author admin
 */
@WebServlet(name="AddPublisherServlet", urlPatterns={"/addPublisher"})
public class AddPublisherServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddPublisherServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddPublisherServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String website = request.getParameter("website");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        
        PublisherDAO pd = new PublisherDAO();
        
        if(name ==null || name.trim().isEmpty()
                || address ==null || address.trim().isEmpty()
                ||website ==null || website.trim().isEmpty()
                || email ==null || email.trim().isEmpty()
                ||phone ==null || phone.trim().isEmpty()){
             request.setAttribute("mess", "Thêm Không Thành Công, Vui lòng nhập đúng giá trị!");
        }else{
            request.setAttribute("mess", "Thêm Thành Công!");
            pd.addPublisher(name, address, website, email, phone);
        }
        // 
       List<Publisher> listP;
       listP =pd.getPublisher();
       
       request.setAttribute("listP", listP);
       request.getRequestDispatcher("listPublisher.jsp").forward(request, response);
        
    } 

     public boolean validatePhoneNumber(String phoneNumber) {
        String regexPhoneNumber = "^(0[3|5|7|8|9])+([0-9]{8})\\b";

        return phoneNumber.matches(regexPhoneNumber);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
