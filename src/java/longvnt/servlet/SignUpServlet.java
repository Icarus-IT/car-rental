/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longvnt.servlet;

import account.tblAccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {

    private final String ERROR_PAGE = "signup.jsp";
    private final String LOGIN_PAGE = "login.jsp";

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
        String url = ERROR_PAGE;
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String name = request.getParameter("txtName");
        String phone = request.getParameter("txtPhone");
        String address = request.getParameter("txtAddress");
        try {
            //1. check 04 faults of user
            boolean foundErr = false;
            if (username.trim().length() == 0) {
                foundErr = true;
                request.setAttribute("USERNAMEERROR", "Username is required not blank!!!");
            }
            if (password.trim().length() == 0) {
                foundErr = true;
                request.setAttribute("PASSWORDERROR", "Password is required not blank!!!");
            } else if (!password.trim().equals(confirm.trim())) {
                foundErr = true;
                request.setAttribute("CONFIRMERROR", "Confirm password wrong!!!");
            }
            if (name.trim().length()==0) {
                foundErr = true;
                request.setAttribute("NAMEERROR", "Name is required not blank!!!");
            }
            if (phone.trim().length()==0) {
                foundErr = true;
                request.setAttribute("PHONEERROR", "Phone is required not blank!!!");
            }
            if (address.trim().length()==0) {
                foundErr = true;
                request.setAttribute("ADDRESSERROR", "Address is required not blank!!!");
            }
            if (foundErr) {
                //2.1 store errors into Scope
               
            } else {
                //2.2 call Dao to insert date to DB
                tblAccountDAO dao = new tblAccountDAO();
                boolean result = dao.createAccount(username, password, phone, name, address);
                if (result) {
                    url = LOGIN_PAGE;
                }
            }

        } catch (SQLException e) {
            String errmsg = e.getMessage();
            log("SignUpServlet _ SQL" + e.getMessage());
            if (errmsg.contains("duplicate")) {
                request.setAttribute("USERNAMEERROR", username + "is existed!!!");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.setCharacterEncoding("UTF-8");

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
