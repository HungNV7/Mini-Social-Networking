/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.sql.Date;
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
public class CommentController extends HttpServlet {

    private final String SUCCESS = "detail.jsp";
    private final String ERROR = "error.jsp";

    private Logger logger = Logger.getLogger(CommentController.class.getName());

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
            String comment = request.getParameter("txtComment");

            if (!comment.isEmpty()) {
                long currentTime = System.currentTimeMillis();
                java.util.Date now = new java.util.Date();
                Date date = new Date(currentTime);

                String commentID = "2" + date.getDate() + (date.getMonth() + 1) + (date.getYear() + 1900) + now.getHours() + now.getMinutes() + now.getSeconds();
                HttpSession session = request.getSession();
                ArticleDTO articleDTO = (ArticleDTO) session.getAttribute("DETAIL_ARTICLE");
                String articleID = articleDTO.getId();
                String email = ((AccountDTO)session.getAttribute("ACCOUNT")).getEmail();
                
                CommentDAO dao= new CommentDAO();
                dao.addComment(new CommentDTO(commentID, articleID, comment, "", "S005", email, date));
                List<CommentDTO> comments = dao.getComments(articleID);
                session.setAttribute("COMMENTS", comments);              
                
                if(!articleDTO.getEmail().equals(email)){
                    String notifyID = "3" + date.getDate() + (date.getMonth() + 1) + (date.getYear() + 1900) + now.getHours() + now.getMinutes() + now.getSeconds();
                    NotificationDAO notificationDAO = new NotificationDAO();
                    notificationDAO.addNotification(new NotificationDTO(notifyID, articleID, email, "Comment", "S007", date, false));
                }
            }
            url = SUCCESS;
        } catch (Exception e) {
            logger.error("Error in CommentController: " + e);
        }finally{
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
