/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longvnt.servlet;

import car.tblCarsDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PC
 */
@WebServlet(name = "PagingServlet", urlPatterns = {"/PagingServlet"})
public class PagingServlet extends HttpServlet {

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
        String button = request.getParameter("btAction");
        String page = request.getParameter("txtPage");
        String lastSearchValue = request.getParameter("lastSearchValue");
        String lastcboCategory = request.getParameter("lastcboCategory");
        String lastcboYear = request.getParameter("lastcboYear");
        String url="";
        try {
            HttpSession session = request.getSession();
            List<tblCarsDTO> list = (List<tblCarsDTO>) session.getAttribute("SEARCHRESULTLIST");
            List<tblCarsDTO> result = new ArrayList<>();
            int pagenum = Integer.parseInt(page);
            if (button.equals("NEXT")) {
                int max=(pagenum + 1) * 5;
                int min=(pagenum * 5);
               
                if (max>list.size()){
                    max=list.size();
                }
                
                for (int i = min ; i < max; i++) {
                   
                    result.add(list.get(i));
                }
                  pagenum++;
            } else {
                int min=(pagenum-2) * 5;
                int max = (pagenum -1) * 5;
                for (int i = min ; i < max ; i++) {
                    result.add(list.get(i));
                }
                  pagenum--;
            }
         
            session.removeAttribute("SEARCHRESULT");
            session.setAttribute("SEARCHRESULT", result);
            url="search?txtSearchValue="+lastSearchValue
                    + "&cboCategory="+lastcboCategory
                    + "&cboYear="+lastcboYear
                    + "&txtPage="+pagenum;
        } finally {
            response.sendRedirect(url);
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
