/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.daos.AccountDAO;
import sample.dtos.AccountDTO;
import sample.dtos.ErrorAccountDTO;
import sample.utilities.Encryption;
import sample.utilities.SendingEmail;

/**
 *
 * @author ASUS
 */
public class RegisterController extends HttpServlet {

    private final String SUCCESS = "verify.jsp";
    private final String ERROR = "register.jsp";
    private Logger log = Logger.getLogger(RegisterController.class.getName());

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
            boolean flag = false;

            String email = request.getParameter("txtEmail");
            String name = request.getParameter("txtName");
            String password = request.getParameter("txtPassword");
            String rePassword = request.getParameter("txtRePassword");

            boolean check = true;
            ErrorAccountDTO error = new ErrorAccountDTO();

            if (!email.matches("[\\w-_\\.]+\\@([\\w]+\\.)+[\\w]+")) {
                check = false;
                error.setErrorEmail("Email is not valid!");
            }
            if (name.length() < 6 || name.length() > 20) {
                check = false;
                error.setErrorName("Name must be 6-20 digits!");
            }
            if (password.length() < 6 || password.length() > 20) {
                check = false;
                error.setErrorPassword("Password must be 6-20 digits!");
            }
            if (!password.equals(rePassword)) {
                check = false;
                error.setErrorRePassword("Two password is not the same!");
            }

            AccountDAO dao = new AccountDAO();
            AccountDTO accountDTO = dao.checkEmail(email);
            if (accountDTO != null) {
                if (accountDTO.getStatus().equals("S001")) {
                    flag = true;
                } else {
                    check = false;
                    error.setErrorEmail("Email have been exist! Please choose another email.");
                }
            }

            if (check) {
                String enryptedPassword = Encryption.toHexString(Encryption.getSHA(password));
                AccountDTO account = new AccountDTO(email, name, enryptedPassword, "Member", "S001");

                HttpSession session = request.getSession();
                session.setAttribute("EMAIL", email);

                Random rd = new Random();
                int code = rd.nextInt(900000) + 100000;
                SendingEmail sendingEmail = new SendingEmail(email, code);
                sendingEmail.sendMail();
                if(flag){
                    dao.updateAccount(account, code);
                }else{
                    dao.addAccount(account, code);
                }
                

                url = SUCCESS;
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("ERROR_REGISTER", error);
            }
        } catch (Exception e) {
            log.error("Error in RegisterController: " + e);
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
