/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
/**
 *
 * @author ASUS
 */
public class MainController extends HttpServlet {
    private Logger logger = Logger.getLogger(MainController.class.getName());
    
    private final String REGISTER_PAGE = "register.jsp";
    private final String LOGIN = "LoginController";
    private final String ERROR = "error.jsp";
    private final String REGISTER = "RegisterController";
    private final String LOGOUT = "LogoutController";
    private final String SEARCH = "SearchController";
    private final String DETAIL = "DetailController";
    private final String CREATE_PAGE = "create.jsp";
    private final String CREATE = "CreateController";
    private final String COMMENT_PAGE = "detail.jsp";
    private final String COMMENT = "CommentController";
    private final String EMOTION = "EmotionController";
    private final String DELETE_POST = "DeletePostController";
    private final String DELETE_COMMENT = "DeleteCommentController";
    private final String VERIFY = "VerifyController";
    private final String NOTIFY = "NotifyController";
    private final String DETAIL_NOTIFY = "DetailNotifyController";

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
            String action = request.getParameter("btnAction");
            if (action.equals("Login")) {
                url = LOGIN;
            } else if (action.equals("Register")) {
                url = REGISTER_PAGE;
            } else if (action.equals("Register Now")) {
                url = REGISTER;
            } else if (action.equals("Logout")) {
                url = LOGOUT;
            } else if (action.equals("Search")) {
                url = SEARCH;
            } else if (action.equals("Detail")) {
                url = DETAIL;
            } else if (action.equals("Create Article")) {
                url = CREATE_PAGE;
            }else if (action.equals("Submit Create Article")) {
                url = CREATE;
            }else if (action.equals("Comment")) {
                url = COMMENT_PAGE;
            }else if (action.equals("Submit Comment")) {
                url = COMMENT;
            }else if (action.contains("Emotion")) {
                url = EMOTION;
            }else if (action.equals("Delete Post")) {
                url = DELETE_POST;
            }else if (action.equals("Delete Comment")) {
                url = DELETE_COMMENT;
            }else if (action.equals("Verification")) {
                url = VERIFY;
            }else if (action.equals("Notify")) {
                url = NOTIFY;
            }else if (action.equals("Detail Notify")) {
                url = DETAIL_NOTIFY;
            }
        } catch (Exception e) {
            logger.error("Error in MainController: " + e);
        }finally{
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
