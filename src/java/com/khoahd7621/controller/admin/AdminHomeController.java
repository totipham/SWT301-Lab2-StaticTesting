package com.khoahd7621.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author KhoaHD7621
 */
@SuppressWarnings("SameReturnValue")
public class AdminHomeController extends HttpServlet {
    
    private static final String ADMIN_PAGE = "admin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            
            Map<Integer, String> listCategories = new CategoryDAO().getCategories();
            List<Plant> listPlants = new PlantDAO().getAllPlants();
            List<Account> listAccounts = new AccountDAO().getAccounts();
            List<Order> listOrders = new OrderDAO().getAllOrders();
            
            session.setAttribute("listCategories", listCategories);
            request.setAttribute("listPlants", listPlants);
            request.setAttribute("listAccounts", listAccounts);
            request.setAttribute("listOrders", listOrders);
            request.setAttribute("destPage", "dashboard");
        } catch (Exception e) {
            log("Error at AdminHomeController: " + e);
        } finally {
            request.getRequestDispatcher(ADMIN_PAGE).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
// --Commented out by Inspection START (10/23/2022 9:00 PM):
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
// --Commented out by Inspection START (10/23/2022 9:00 PM):
////            throws ServletException, IOException {
////        processRequest(request, response);
////    }
//// --Commented out by Inspection STOP (10/23/2022 9:00 PM)
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
// --Commented out by Inspection STOP (10/23/2022 9:00 PM)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
