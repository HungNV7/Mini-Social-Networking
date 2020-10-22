/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.daos.ArticleDAO;
import sample.dtos.AccountDTO;
import sample.dtos.ArticleDTO;
import org.apache.log4j.Logger;
/**
 *
 * @author ASUS
 */
public class CreateController extends HttpServlet {

    private final String SUCCESS = "SearchController";
    private final String ERROR = "create.jsp";
    private Logger logger = Logger.getLogger(CreateController.class.getName());

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
            HttpSession session = request.getSession();
            
            String title = request.getParameter("txtTitle");
            String descriptionDetail = request.getParameter("txtDescription");
            String image = request.getParameter("txtImage");

            if (!title.isEmpty() || !descriptionDetail.isEmpty() || !image.isEmpty()) {
                long currentTime = System.currentTimeMillis();
                java.util.Date now = new java.util.Date();
                Date date = new Date(currentTime);

                String id = "1" + date.getDate() + (date.getMonth() + 1) + (date.getYear() + 1900) + now.getHours() + now.getMinutes() + now.getSeconds();
                List<String> description = new ArrayList<String>();
                description.add(descriptionDetail);
                
                String email = ((AccountDTO)session.getAttribute("ACCOUNT")).getEmail();
                ArticleDTO article = new ArticleDTO(id, title, image, "S004", image, email, description, date);
                
                ArticleDAO dao = new ArticleDAO();          
                
                dao.addArticle(article);
                url = SUCCESS + "?txtSearch=";
                request.getRequestDispatcher(url).forward(request, response);
            }else{
                session.setAttribute("ERROR_CREATE_ARTICLE", "All field above can not be empty!");
                response.sendRedirect(url);
            }

        } catch (Exception e) {
            logger.error("Error in CreateController: " + e);
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
