/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longvnt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import order.tbl_ordersDTO;

/**
 *
 * @author USER
 */
@WebServlet(name = "AddtoCartServlet", urlPatterns = {"/AddtoCartServlet"})
public class AddtoCartServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        
        String email = (String) session.getAttribute("EMAIL");
        String carid = request.getParameter("carid");
        String name = (String) session.getAttribute("USERNAME");
        String carname = request.getParameter("carname");
        String price = request.getParameter("price");
        
        String searchValue = request.getParameter("lastSearchValue");
        String searchCategory = request.getParameter("lastcategoryValue");
        String searchYear = request.getParameter("lastyearValue");
        String page = request.getParameter("txtPage");
        
        String urlRewriting = null;

        try {
            tbl_ordersDTO dto = new tbl_ordersDTO(email, carid, name, carname, Float.parseFloat(price), 1);
            
            HashMap<tbl_ordersDTO, Integer> cart = (HashMap<tbl_ordersDTO, Integer>) session.getAttribute("CUSTCART");
            if (cart == null) {
                cart = new HashMap<>();
            }

            int quantity = 1;
            for (tbl_ordersDTO x : cart.keySet()) {

                if (x.getCarid().equals(dto.getCarid())) {

                    quantity = quantity + 1;
                    dto = x;
                }

            }
            urlRewriting = "search"
                    + "?btAction=search"
                    + "&txtSearchValue=" + searchValue
                    + "&cboCategory=" + searchCategory
                    + "&cboYear=" + searchYear
                    + "&txtPage=" + page;
            dto.setQuantity(quantity);
            cart.put(dto, quantity);

            session.setAttribute("CUSTCART", cart);
        } finally {

            response.sendRedirect(urlRewriting);

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
