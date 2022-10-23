package com.khoahd7621.controller.sync;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
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
public class LoadMoreController extends HttpServlet {

// --Commented out by Inspection START (10/23/2022 9:01 PM):
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException {
//        response.setContentType("text/html;charset=UTF-8");
//        try {
//            HttpSession session = request.getSession();
//            Map<Integer, String> listCategories = (Map<Integer, String>) session.getAttribute("listCategories");
//            PrintWriter out = response.getWriter();
//            String action = request.getParameter("action");
//            List<Plant> list;
//            Object[] listObj;
//            String json;
//            Gson gson = new Gson();
//            if (action != null) {
//                switch (action) {
//                    case "loadMoreOurProduct":
//                        int amount = Integer.parseInt(request.getParameter("exists"));
//                        list = new PlantDAO().getNext4Plants(amount);
//                        listObj = new Object[]{listCategories, list};
//                        json = gson.toJson(listObj);
//                        response.getWriter().println(json);
//                        break;
//                    case "loadProductsById":
//                        int cateId = Integer.parseInt(request.getParameter("cateId"));
//                        list = new PlantDAO().getTop4PlantsByCateId(cateId);
//                        listObj = new Object[]{listCategories, list};
//                        json = gson.toJson(listObj);
//                        response.getWriter().println(json);
//                        break;
//                }
//            }
//        } catch (Exception e) {
//            log("Error at LoadMoreController: " + e);
//        }
//    }
// --Commented out by Inspection STOP (10/23/2022 9:01 PM)

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
