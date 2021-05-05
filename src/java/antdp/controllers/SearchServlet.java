/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.controllers;

import antdp.daos.CarDAO;
import antdp.dtos.CarDTO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author HP 840 G2
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SearchServlet.class);

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

        String url = "search.jsp";
        String name = request.getParameter("txtName");
        String rentalDate = request.getParameter("txtRentalDate");
        String returnDate = request.getParameter("txtReturnDate");
        String category = request.getParameter("txtCategory");
        String indexString = request.getParameter("index");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (category.equals("All")) {
            category = "";
        }
        int index = 0;
        if (indexString == null || indexString.equals("")) {
            index = 1;
        } else {
            index = Integer.parseInt(indexString);
        }

        try {
            if (sdf.parse(returnDate).compareTo(sdf.parse(rentalDate)) <= 0) {
                request.setAttribute("ERROR", "Return day have to after rental date");
            } else {
                CarDAO dao = new CarDAO();
                int count = dao.countPage(rentalDate, returnDate, name, category);
                int pageSize = 3;
                int endPage = 0;
                endPage = count / pageSize;
                if (count % pageSize != 0) {
                    endPage++;
                }
                request.setAttribute("endPage", endPage);
                int row = (index - 1) * pageSize;
                List<CarDTO> list = dao.search(rentalDate, returnDate, name, category, row, pageSize);
                request.setAttribute("LISTCAR", list);
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
