/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.controllers;

import antdp.history.HistoryRental;
import antdp.history.RentalCarDTO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
@WebServlet(name = "SearchHistoryServlet", urlPatterns = {"/SearchHistoryServlet"})
public class SearchHistoryServlet extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(SearchHistoryServlet.class);

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
        
        String name = request.getParameter("txtSearchName");
        String date = request.getParameter("txtSearchDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            HttpSession session = request.getSession();
            HistoryRental historyRental = (HistoryRental) session.getAttribute("HistoryRental");
            List<String> listOrderId_remove = new ArrayList<>();    // lấy lại map all history 

            for (String orderID : historyRental.getHistoryRental().keySet()) {    //chạy từng bill 
                List<RentalCarDTO> listFood_Bill = historyRental.getHistoryRental().get(orderID);                
                boolean check = false;
                for (RentalCarDTO rentalCarDTO : listFood_Bill) {                 // chạy từng sản phẩm trong bill
                    String carName = rentalCarDTO.getCarInRental().getName();
                    String dateOfCreate = sdf.format(rentalCarDTO.getDateOfCreate());
                    
                    if (carName.contains(name)
                            && dateOfCreate.equals(sdf.format(sdf.parse(date)))) {  // nếu thỏa mãn đk search --> check = true
                        check = true;
                    }
                }
                if (check == false) {                            // không thỏa mãn check vẫn = false và add vào list id để remove 
                    listOrderId_remove.add(orderID);                    
                }
                
            }
            for (String orderID : listOrderId_remove) {       // remove  
                historyRental.remove(orderID);
            }
            session.setAttribute("HistoryRental", historyRental);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            request.getRequestDispatcher("historyRental.jsp").forward(request, response);
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
