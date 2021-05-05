/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.controllers;

import antdp.cart.CartObj;
import antdp.daos.CarDAO;
import antdp.dtos.CarDTO;
import antdp.dtos.RegistrationDTO;
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
@WebServlet(name = "AddCarToCardServlet", urlPatterns = {"/AddCarToCardServlet"})
public class AddCarToCardServlet extends HttpServlet {

    private static final String ERROR_PAGE = "error.jsp";
    private static final Logger LOGGER = Logger.getLogger(AddCarToCardServlet.class);

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

        String rentalDate = request.getParameter("txtRentalDate");
        String returnDate = request.getParameter("txtReturnDate");
        String carID = request.getParameter("txtCarID");
        String maxQuantity = request.getParameter("maxQuantity");
        String url = ERROR_PAGE;

        url = "SearchServlet";
        try {
            HttpSession session = request.getSession();
            CartObj cartObj = (CartObj) session.getAttribute("Cart");
            RegistrationDTO registrationDTO = (RegistrationDTO) session.getAttribute("REGISTRATION");
            String email = null;
            if (registrationDTO != null) {
                email = registrationDTO.getEmail();
            }

            if (cartObj == null || cartObj.getCart() == null) {   // cart chưa có thì không cần check ngày 
                cartObj = new CartObj(email, rentalDate, returnDate);
                CarDAO dao = new CarDAO();
                CarDTO dto = dao.getCarbyID(carID);
                dto.setMaxQuantity(Integer.parseInt(maxQuantity));
                dto.setQuantity(1);
                cartObj.addToCart(dto);
                session.setAttribute("Cart", cartObj);

            } else {                  // có cart thì check ngày xem user có search lại ngày khác không
                boolean checkError = false;
                if (!cartObj.getRentalDate().equals(rentalDate) || !cartObj.getReturnDate().equals(returnDate)) {
                    checkError = true;
                }
                if (checkError) {
                    request.setAttribute("DATE_ERROR_CART", "conflict date");
                } else {
                    CarDAO dao = new CarDAO();
                    CarDTO dto = dao.getCarbyID(carID);
                    dto.setMaxQuantity(Integer.parseInt(maxQuantity));
                    dto.setQuantity(1);
                    cartObj.addToCart(dto);
                    session.setAttribute("Cart", cartObj);
                }
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
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
