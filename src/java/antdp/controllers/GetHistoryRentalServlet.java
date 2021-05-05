/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.controllers;

import antdp.daos.CarDAO;
import antdp.daos.RentalBillDAO;
import antdp.dtos.CarDTO;
import antdp.dtos.RegistrationDTO;
import antdp.dtos.RentalBillDetailDTO;
import antdp.dtos.RentalDTO;
import antdp.history.HistoryRental;
import antdp.history.RentalCarDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@WebServlet(name = "GetHistoryRentalServlet", urlPatterns = {"/GetHistoryRentalServlet"})
public class GetHistoryRentalServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(GetHistoryRentalServlet.class);

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
        String url = "";
        if (btnAction == null) {
            url = "historyRental.jsp";
        } else {
            url = "SearchHistoryServlet";
        }
        try {
            HistoryRental historyOrder = new HistoryRental();
            HttpSession session = request.getSession();
            RegistrationDTO registrationDTO = (RegistrationDTO) session.getAttribute("REGISTRATION");
            String email = null;
            if (registrationDTO != null) {
                email = registrationDTO.getEmail();
            }
            RentalBillDAO rentalBillDAO = new RentalBillDAO();
            CarDAO carDAO = new CarDAO();
            List<RentalDTO> listBill = rentalBillDAO.getRentalByUsername(email);  // get list rental by username
            for (RentalDTO rentalDTO : listBill) {    // chạy từng item trong bảng Rental
                List<RentalBillDetailDTO> listRentalDetail = rentalBillDAO.getRentalDetailByRentalID(rentalDTO.getRentalID());  // get list rental detail by rentalID
                float total = rentalDTO.getTotal();    // get total bảng order
                Date date = rentalDTO.getDateOfCreate();
                List<RentalCarDTO> listCar_Bill = new ArrayList<>();           //  new List List<FoodOrderDTO>
                for (RentalBillDetailDTO rentalBillDetailDTO : listRentalDetail) {
                    CarDTO carDTO = carDAO.getCarbyID(rentalBillDetailDTO.getCarID());
                    float price = rentalBillDetailDTO.getPrice();
                    int quantity = rentalBillDetailDTO.getQuantity();
                    String rentalDate = rentalBillDetailDTO.getRentalDate();
                    String returnDate = rentalBillDetailDTO.getReturnDate();
                    RentalCarDTO rentalCarDTO = new RentalCarDTO(carDTO, total, price, quantity, rentalDate, returnDate, date, rentalDTO.getRentalID());
                    listCar_Bill.add(rentalCarDTO);
                }
                historyOrder.add(rentalDTO.getRentalID(), listCar_Bill);
            }
            session.setAttribute("HistoryRental", historyOrder);
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
