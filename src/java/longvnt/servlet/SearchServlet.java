/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longvnt.servlet;

import account.tblAccountDTO;
import car.tblCarsDAO;
import car.tblCarsDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USER
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    private final String SEARCH_PAGE = "search.jsp";
    private final String SHOW_PAGE = "search.jsp";

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
        PrintWriter out = response.getWriter();
        String searchValue = request.getParameter("txtSearchValue");
        String year = request.getParameter("cboYear");
        String searchCategory = request.getParameter("cboCategory");
        String url = SEARCH_PAGE;

        try {
            //search value has

            tblCarsDAO dao = new tblCarsDAO();

            List<tblCarsDTO> result = dao.getAllCar();
            List<tblCarsDTO> list = new ArrayList<>();

            if (searchValue.length() == 0) {
                list = result;
            } else {
                for (tblCarsDTO dto : result) {
                    if (dto.getCarname().toLowerCase().equals(searchValue.toLowerCase())) {
                        list.add(dto);
                    }
                }
            }

            List<tblCarsDTO> list1 = new ArrayList<>();

            if (searchCategory.equals("All")) {
                list1 = list;
            } else {
                for (tblCarsDTO dto : list) {
                    if (dto.getCategory().toLowerCase().equals(searchCategory.toLowerCase())) {
                        list1.add(dto);
                    }
                }
            }

            List<tblCarsDTO> list2 = new ArrayList<>();

            if (year.equals("All")) {
                list2 = list1;
            } else {
                for (tblCarsDTO dto : list1) {
                    if (dto.getYear().equals(year)) {
                        list2.add(dto);
                    }
                }
            }
//                System.out.println(result.size());
//                System.out.println(list.size());
//                System.out.println(list1.size());
//                System.out.println(list2.size());
            List<tblCarsDTO> list3 = new ArrayList<>();
            int n = list2.size();
            if (n > 5) {
                n = 5;
            }
            String button = request.getParameter("btAction");
             
                for (int i = 0; i < n; i++) {
                    list3.add(list2.get(i));
                }
            

            HttpSession session = request.getSession();
            if (("Search").equals(button)) {
                session.setAttribute("SEARCHRESULT", list3);
            } else {

            }

            session.setAttribute("SEARCHRESULTLIST", list2);
            int pagemax;
            if (list2.size() % 5 == 0) {
                pagemax = list2.size() / 5;
            } else {
                pagemax = list2.size() / 5 + 1;
            }
            session.setAttribute("PAGEMAX", pagemax);

            url = SHOW_PAGE;

        } catch (ClassNotFoundException ex) {
            log("ClassNotFoundException in SearchServlet " + ex.getMessage());
        } catch (SQLException ex) {
            log("SQLException in SearchServlet " + ex.getMessage());
        } finally {

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
