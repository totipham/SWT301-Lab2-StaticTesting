package com.khoahd7621.controller.sync;

import java.io.IOException;
import java.util.List;
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
public class SearchOrderController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession(true);
            Account account = (Account) session.getAttribute("LOGIN_USER");
            String from = request.getParameter("from");
            String to = request.getParameter("to");
            List<Order> orderList = new OrderDAO().getOrdersByPeriodTime(account.getAccId(), from, to);
            if (orderList != null && orderList.size() > 0) {
                request.setAttribute("orderList", orderList);
                request.setAttribute("from", from);
                request.setAttribute("to", to);
                session.setAttribute("urlOrderHistory", "SearchOrderServlet?from=" + from + "&to=" + to);
                request.getRequestDispatcher("order.jsp").forward(request, response);
            } else {
                request.setAttribute("from", from);
                request.setAttribute("to", to);
                request.setAttribute("NO_ORDER", "You don't have any order from " + from + " to " + to);
                request.getRequestDispatcher("order.jsp").forward(request, response);
            }
        } catch (Exception e) {
            log("Error at SearchOrderController: " + e);
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
