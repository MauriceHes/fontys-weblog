/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Service.WebLogService;
import dao.WebLogDaoImp;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Comment;
import model.Posting;

/**
 *
 * @author Jurgen
 */
@WebServlet(name = "MyBlogController", urlPatterns = {"/blog", "/admin", "/addpost", "/viewpost", "/addcomment"})
//@WebServlet(value="/blog")
public class MyBlogController extends HttpServlet {

    HttpSession session;
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
        session = request.getSession();

        //het volgende blok zorgt ervoor dat p de huidige pagina aanduidt
        String uri = request.getRequestURI().replace(request.getContextPath() + "/", "");
        PageEnum p;

        try{
            p = PageEnum.valueOf(uri.toUpperCase());
        }catch(IllegalArgumentException iaex){
            p = PageEnum.BLOG;
        }
        
        switch (p) {
            case BLOG:
                //haalt alle posts op uit de service en zendt deze door naar blog.jsp
                request.setAttribute("posts", weblogService.getPostings());
                RequestDispatcher indexView = request.getRequestDispatcher("view/blog.jsp");
                indexView.forward(request, response);
                break;
            case VIEWPOST:
                //haal de post op bij het gegeven ID en zendt deze door naar viewpost.jsp
                request.setAttribute("post", weblogService.getPost(Long.valueOf(request.getParameter("id"))));
                RequestDispatcher viewPostView = request.getRequestDispatcher("view/viewpost.jsp");
                viewPostView.forward(request, response);
                break;
            case ADMIN:
                //wanneer er geen admin modus is ingesteld, zet hem standaard op basic
                if(session.getAttribute("mode") == null)
                {
                    session.setAttribute("mode", "basic");
                }

                //wanneer gevraagd, verander de admin modus
                if(request.getParameter("mode") != null)
                {
                    if(request.getParameter("mode").equals("advanced"))
                    {
                        session.setAttribute("mode", "advanced");

                    }else{
                        session.setAttribute("mode", "basic");
                    }
                }

                //wanneer gevraagd, verwijder de post bij het gegeven ID
                if(request.getParameter("delete") != null)
                {
                    Long deleteID = Long.parseLong(request.getParameter("delete"));
                    weblogService.deletePosting(deleteID);
                }

                //wanneer een EDIT gedaan wordt, haal de bijbehorende post op
                if(request.getParameter("edit") != null)
                {
                    Long editID = Long.parseLong(request.getParameter("edit"));
                    request.setAttribute("editPost", weblogService.getPost(editID));
                }

                //haalt alle posts op uit de service en zendt deze door naar admin.jsp
                request.setAttribute("posts", weblogService.getPostings());
                RequestDispatcher adminView = request.getRequestDispatcher("view/admin.jsp");
                adminView.forward(request, response);
                break;
            case ADDPOST:
                //addpost kent twee acties
                //1) "new" voegt een nieuwe post toe, hiervoor is geen ID benodigd
                //2) "edit" wijzigt de post bij het gegeven ID
                if(request.getParameter("action").equals("new"))
                {
                    Posting newPost = new Posting(request.getParameter("inputtitle"), request.getParameter("inputbody"));
                    weblogService.addPosting(newPost);
                }else if(request.getParameter("action").equals("edit"))
                {
                    Posting editPost = weblogService.getPost(Long.parseLong(request.getParameter("postid")));
                    editPost.setContent(request.getParameter("inputbody"));
                    editPost.setTitle(request.getParameter("inputtitle"));
                }
                //haalt alle posts op uit de service en zendt deze door naar admin.jsp
                request.setAttribute("posts", weblogService.getPostings());
                RequestDispatcher addPostView = request.getRequestDispatcher("view/admin.jsp");
                addPostView.forward(request, response);
                
                break;
            case ADDCOMMENT:
                //voegt de comment toe bij het gegeven ID
                Long postid = Long.parseLong(request.getParameter("postid"));
                Posting post = weblogService.getPost(postid);
                Comment newComment = new Comment(post.getNextCommentID(), request.getParameter("commentbody"));
                post.setComment(newComment);

                //omdat de aanvraag door Ajax gedaan wordt, 
                //returnen we direct de div waarin de comment staat via de writer
                PrintWriter out = response.getWriter();
                String commentbody = request.getParameter("commentbody");
                try {
                    out.println("<div class='comment'>"+commentbody+"</div>");
                } finally {
                    out.close();
                }
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
