/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.daos.CommentDAO;
import sample.daos.NotificationDAO;
import sample.dtos.AccountDTO;
import sample.dtos.ArticleDTO;
import sample.dtos.CommentDTO;
import sample.dtos.NotificationDTO;

/**
 *
 * @author ASUS
 */
public class DeleteCommentController extends HttpServlet {

    private final String SUCCESS = "detail.jsp";
    private final String ERROR = "error.jsp";
    private Logger logger = Logger.getLogger(DeleteCommentController.class.getName());

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
        String url = ERROR;
        try {
            String commentID = request.getParameter("commentID");

            CommentDAO commentDAO = new CommentDAO();
            commentDAO.deleteComment(commentID);

            HttpSession session = request.getSession();
            ArticleDTO article = (ArticleDTO) session.getAttribute("DETAIL_ARTICLE");
            String articleID = article.getId();

            String email = ((AccountDTO) session.getAttribute("ACCOUNT")).getEmail();

            if (!article.getEmail().equals(email)) {
                NotificationDAO notificationDAO = new NotificationDAO();
                NotificationDTO notificationDTO = notificationDAO.getNotification(articleID, email, "Comment");
                notificationDTO.setStatus("S008");
                notificationDAO.updateNotification(notificationDTO);
            }

            List<CommentDTO> comments = commentDAO.getComments(articleID);
            session.setAttribute("COMMENTS", comments);

            url = SUCCESS;
        } catch (Exception e) {
            logger.error("Error in DeleteCommentController: " + e);
        } finally {
            response.sendRedirect(url);
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
