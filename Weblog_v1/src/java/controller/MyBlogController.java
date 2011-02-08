/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Service.WebLogService;
import dao.WebLogDaoImp;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jurgen
 */
@WebServlet(name = "MyBlogController", urlPatterns = {"/blog", "/admin", "/addPost"})
//@WebServlet(value="/blog")
public class MyBlogController extends HttpServlet {

    WebLogService weblogService;

    @Override
    public void init() throws ServletException {
        super.init();

        weblogService = new WebLogService();
    }


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
        
        String uri = request.getRequestURI().replace(request.getContextPath() + "/", "");
        PageEnum p;

        try{
            p = PageEnum.valueOf(uri.toUpperCase());
        }catch(IllegalArgumentException iaex){
            p = PageEnum.BLOG;
        }
        
        switch (p) {
            case BLOG:
                RequestDispatcher indexView = request.getRequestDispatcher("view/index.jsp");
                indexView.forward(request, response);
                break;
            case ADMIN:
                RequestDispatcher adminView = request.getRequestDispatcher("view/admin.jsp");
                adminView.forward(request, response);
                break;
            case ADDPOST:
                RequestDispatcher addPostView = request.getRequestDispatcher("view/admin.jsp");
                addPostView.forward(request, response);
                break;
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
        processRequest(request, response);
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
