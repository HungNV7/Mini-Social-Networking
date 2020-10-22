/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.daos.ArticleDAO;
import sample.dtos.ArticleDTO;

/**
 *
 * @author ASUS
 */
public class SearchController extends HttpServlet {

    private final String SUCCESS = "search.jsp";
    private final String ERROR = "error.jsp";
    private Logger logger = Logger.getLogger(SearchController.class.getName());

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

            String page = request.getParameter("page");
            String search = request.getParameter("txtSearch");
            String other = request.getParameter("other");

            if (page == null) {
                page = "1";
            }
            int index = Integer.parseInt(page);
            if (other != null) {
                if (other.equals("prev")) {
                    index = (index - 1) == 0 ? 1 : index - 1;
                } else {
                    int count = (Integer) session.getAttribute("COUNT");
                    index = (index + 1) == (count + 1) ? count : index + 1;
                }
            }
            request.setAttribute("page", index);

            ArticleDAO dao = new ArticleDAO();
            List<ArticleDTO> list = dao.getListArticle(search, (index - 1) * 5);

            if (!list.isEmpty()) {

                if (session.getAttribute("COUNT") == null || index == 1) {
                    int count = (int) Math.ceil((double) dao.getCount(search) / 5);
                    session.setAttribute("COUNT", count);
                }
            }
            session.setAttribute("LIST_ARTICLE", list);
            url = SUCCESS;

        } catch (Exception e) {
            logger.error("Error in SearchController: " + e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
