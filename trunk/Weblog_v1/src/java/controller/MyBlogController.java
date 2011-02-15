/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Service.WebLogService;
import dao.WebLogDaoImp;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Comment;
import model.Posting;

/**
 *
 * @author Jurgen
 */
@WebServlet(name = "MyBlogController", urlPatterns = {"/blog", "/admin", "/addpost", "/viewpost", "/addcomment"})
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
                request.setAttribute("posts", weblogService.getPostings());
                RequestDispatcher indexView = request.getRequestDispatcher("view/blog.jsp");
                indexView.forward(request, response);
                break;
            case VIEWPOST:
                request.setAttribute("post", weblogService.getPost(Long.valueOf(request.getParameter("id"))));
                RequestDispatcher viewPostView = request.getRequestDispatcher("view/viewpost.jsp");
                viewPostView.forward(request, response);
                break;
            case ADMIN:
                request.setAttribute("posts", weblogService.getPostings());
                RequestDispatcher adminView = request.getRequestDispatcher("view/admin.jsp");
                adminView.forward(request, response);
                break;
            case ADDPOST:
                Posting newPost = new Posting(request.getParameter("inputtitle"), request.getParameter("inputbody"));
                weblogService.addPosting(newPost);
                request.setAttribute("posts", weblogService.getPostings());
                RequestDispatcher addPostView = request.getRequestDispatcher("view/admin.jsp");
                addPostView.forward(request, response);
                break;
            case ADDCOMMENT:
                Long postid = Long.parseLong(request.getParameter("postid"));
                Posting post = weblogService.getPost(postid);
                Comment newComment = new Comment(post.getNextCommentID(), request.getParameter("commentbody"));
                post.setComment(newComment);

                PrintWriter out = response.getWriter();
                String commentbody = request.getParameter("commentbody");
                try {
                    out.println("<div class='comment'>"+commentbody+"</div>");
                } finally {
                    out.close();
                }
                //request.setAttribute("post", post);
                //RequestDispatcher addCommentView = request.getRequestDispatcher("view/viewpost.jsp");
                //addCommentView.forward(request, response);
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
