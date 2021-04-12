/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longvnt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
@WebServlet(name = "RemoveCarServlet", urlPatterns = {"/RemoveCarServlet"})
public class RemoveCarServlet extends HttpServlet {

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
        try {
            HttpSession session = request.getSession(false);
            tbl_ordersDTO dto = new tbl_ordersDTO();
            if (session != null) {
                //2. Cust takes his/her cart
                HashMap<tbl_ordersDTO, Integer> cart = (HashMap<tbl_ordersDTO, Integer>) session.getAttribute("CUSTCART");
                if (cart != null) {

                    String[] removedItems = request.getParameterValues("chkItem");
                    if (removedItems != null) {
                        for (String item : removedItems) {

                            boolean check = false;
                            for (tbl_ordersDTO x : cart.keySet()) {
                                if (x.getCarid().equals(item)) {
                                    check = true;
                                    dto = x;
                                }
                            }
                            if (check) {
                                cart.remove(dto);
                                if (cart.isEmpty()) {
                                    cart = null;
                                }
                            }
                        }// end for item
                        session.setAttribute("CUSTCART", cart);

                    }// having selected
                }// end if items is existed

                if (session.getAttribute("OUTOFSTOCKERROR") != null) {
                    session.removeAttribute("OUTOFSTOCKERROR");
                }
            }// end if session is existed
        } finally {
            String urlRewriting = "viewyourcart"
                    + "?btAction=Cart";
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
