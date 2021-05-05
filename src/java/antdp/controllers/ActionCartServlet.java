/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.controllers;

import antdp.cart.CartObj;
import antdp.daos.RentalBillDAO;
import antdp.dtos.CarDTO;
import antdp.dtos.RentalBillDetailDTO;
import antdp.dtos.RentalDTO;
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
@WebServlet(name = "ActionCartServlet", urlPatterns = {"/ActionCartServlet"})
public class ActionCartServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ActionCartServlet.class);

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

        String btnAction = request.getParameter("btnAction");
        String[] listID = request.getParameterValues("txtCarID");
        String[] listQuantity = request.getParameterValues("txtQuantity");
        String url = "viewCart.jsp";
        try {
            HttpSession session = request.getSession();
            CartObj cartObj = (CartObj) session.getAttribute("Cart");
            if (btnAction.equals("Update")) {
                for (int i = 0; i < listID.length; i++) {
                    cartObj.updateCart(listID[i], Integer.parseInt(listQuantity[i]));
                }
                session.setAttribute("Cart", cartObj);

            } else if (btnAction.equals("Remove")) {
                String[] listID_remove = request.getParameterValues("chkRemove");
                for (int i = 0; i < listID_remove.length; i++) {
                    cartObj.remove(listID_remove[i]);
                }
                session.setAttribute("Cart", cartObj);

            } else if (btnAction.equals("Rent")) {
                url = "SearchServlet";
                for (int i = 0; i < listID.length; i++) {
                    cartObj.updateCart(listID[i], Integer.parseInt(listQuantity[i]));
                }
                session.setAttribute("Cart", cartObj);
                RentalBillDAO dao = new RentalBillDAO();
                String lastID = dao.getLastID(cartObj.getCustomerName());
                String rentalID = "";            // orderId = OD-customerName-number
                if (lastID == null) {
                    rentalID = "RENT-" + cartObj.getCustomerName() + "-1";
                } else {
                    String[] tmp = lastID.split("-");
                    rentalID = "RENT-" + cartObj.getCustomerName() + "-" + (Integer.parseInt(tmp[2]) + 1);
                }

                RentalDTO rentalDTO = new RentalDTO(rentalID, cartObj.getCustomerName(), cartObj.getTotal());
                if (dao.createRentalBill(rentalDTO)) {         // nếu tạo bill thành công thì add bill detail
                    int count = 0;
                    for (CarDTO carInCart : cartObj.getCart().values()) {
                        String rentalDetailID = rentalID + "-" + count;       // orderDetailID = orderID-number
                        RentalBillDetailDTO rentalBillDetailDTO = new RentalBillDetailDTO(rentalDetailID, rentalID,
                                carInCart.getCarID(), carInCart.getQuantity(), carInCart.getPrice(), cartObj.getRentalDate(), cartObj.getReturnDate());
                        if (dao.createRentalBillDetail(rentalBillDetailDTO)) {
                            count++;
                        }
                    }
                }
                session.removeAttribute("Cart");
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
