/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.controllers;

import antdp.daos.RegistrationDAO;
import antdp.dtos.RegistrationDTO;
import antdp.utilities.VerifyRecaptcha;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author HP 840 G2
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    private static final long serialVersionUID = 1L;

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

        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String url = "login.jsp";
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);

        try {
            HttpSession session = request.getSession();
            RegistrationDAO dao = new RegistrationDAO();
            RegistrationDTO registrationDTO = dao.checkLogin(email, password);
            if (registrationDTO != null) {
                if (registrationDTO.getStatus().equals("Active") && verify) {
                    url = "GetAllCarServlet";
                    session.setAttribute("REGISTRATION", registrationDTO);
                    response.sendRedirect(url);
                } else if (registrationDTO.getStatus().equals("New") && verify) {
                    url = "SendCodeToEmailServlet";
                    session.setAttribute("REGISTRATION", registrationDTO);
                    request.getRequestDispatcher(url).forward(request, response);
                }
            } else {
                if (verify) {
                    request.setAttribute("ERROR", "Username or password is wrong");
                } else {
                    request.setAttribute("ERROR", "You missed the Captcha");
                }
                request.getRequestDispatcher(url).forward(request, response);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
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
