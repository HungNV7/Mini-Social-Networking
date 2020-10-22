/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.daos.EmotionDAO;
import sample.daos.NotificationDAO;
import sample.dtos.AccountDTO;
import sample.dtos.ArticleDTO;
import sample.dtos.EmotionDTO;
import sample.dtos.NotificationDTO;

/**
 *
 * @author ASUS
 */
public class EmotionController extends HttpServlet {

    private final String SUCCESS = "detail.jsp";
    private final String ERROR = "error.jsp";

    private Logger logger = Logger.getLogger(EmotionController.class.getName());

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
            String emotion = request.getParameter("btnAction").split("-")[1];
            EmotionDTO emotionDTO = (EmotionDTO) session.getAttribute("EMOTION");
            String currentEmotion = emotionDTO == null ? "" : emotionDTO.getIcon();
            String icon = "";

            if (!emotion.equals(currentEmotion)) {
                icon = emotion;
            }

            EmotionDAO emotionDAO = new EmotionDAO();
            NotificationDAO notificationDAO = new NotificationDAO();

            ArticleDTO articleDTO = (ArticleDTO) session.getAttribute("DETAIL_ARTICLE");
            String articleID = articleDTO.getId();
            String email = ((AccountDTO) session.getAttribute("ACCOUNT")).getEmail();

            long currentTime = System.currentTimeMillis();
            java.util.Date now = new java.util.Date();
            Date date = new Date(currentTime);
            if (emotionDTO == null) {
                String emotionID = "3" + date.getDate() + (date.getMonth() + 1) + (date.getYear() + 1900) + now.getHours() + now.getMinutes() + now.getSeconds();

                emotionDTO = new EmotionDTO(emotionID, articleID, email, icon, date);
                emotionDAO.insertEmotion(emotionDTO);

                if (!articleDTO.getEmail().equals(email)) {
                    String notifyID = "4" + date.getDate() + (date.getMonth() + 1) + (date.getYear() + 1900) + now.getHours() + now.getMinutes() + now.getSeconds();
                    notificationDAO.addNotification(new NotificationDTO(notifyID, articleID, email, icon, "S007", date, false));
                }
            } else {
                emotionDTO.setIcon(icon);
                emotionDAO.updateEmotion(emotionDTO);

                if (!articleDTO.getEmail().equals(email)) {
                    NotificationDTO notificationDTO = notificationDAO.getNotification(articleID, email, currentEmotion);
                    notificationDTO.setType(icon);
                    notificationDTO.setDate(date);
                    notificationDTO.setIsRead(false);
                    if (icon.equals("")) {
                        notificationDTO.setStatus("S008");
                    } else {
                        notificationDTO.setStatus("S007");
                    }

                    notificationDAO.updateNotification(notificationDTO);
                }

            }
            session.setAttribute("EMOTION", emotionDTO);
            session.setAttribute("LIKE", emotionDAO.getEmotions("Like", articleID));
            session.setAttribute("DISLIKE", emotionDAO.getEmotions("Dislike", articleID));
            url = SUCCESS;
        } catch (Exception e) {
            logger.error("Error in EmotionController: " + e);
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
