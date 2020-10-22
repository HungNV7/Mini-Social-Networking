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
import sample.daos.ArticleDAO;
import sample.daos.CommentDAO;
import sample.daos.EmotionDAO;
import sample.dtos.AccountDTO;
import sample.dtos.ArticleDTO;
import sample.dtos.CommentDTO;
/**
 *
 * @author ASUS
 */
public class DetailController extends HttpServlet {
    private final String SUCCESS = "detail.jsp";
    private final String ERROR = "error.jsp";
    private Logger logger = Logger.getLogger(DetailController.class.getName());
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
            String id = request.getParameter("id");
            
            CommentDAO cmtDAO = new CommentDAO();
            ArticleDAO articleDAO = new ArticleDAO();
            EmotionDAO emotionDAO = new EmotionDAO();
            HttpSession session = request.getSession();
            
            List<CommentDTO> comments = cmtDAO.getComments(id);
            ArticleDTO article = articleDAO.getArticle(id);
            AccountDTO account = (AccountDTO) session.getAttribute("ACCOUNT");
            
            session.setAttribute("DETAIL_ARTICLE", article);
            session.setAttribute("COMMENTS", comments);
            session.setAttribute("LIKE", emotionDAO.getEmotions("Like", article.getId()));
            session.setAttribute("DISLIKE", emotionDAO.getEmotions("Dislike", article.getId()));
            session.setAttribute("EMOTION", emotionDAO.getOwnEmotion(article.getId(), account.getEmail()));
            url = SUCCESS;
            
        } catch (Exception e) {
            logger.error("Error in DetailController: "+ e);
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
