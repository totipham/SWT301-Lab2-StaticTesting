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
public class ViewAllController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            final int PAGE_SIZE = 9;
            
            HttpSession session = request.getSession();
            session.setAttribute("destPage", "plant");
            
            String category = request.getParameter("category");
            String pagenumber = request.getParameter("pagenumber");
            int page = 1;
            if (pagenumber != null) {
                page = Integer.parseInt(pagenumber);
            }

            PlantDAO dao = new PlantDAO();
            if (category == null || category.isEmpty()) {
                List<Plant> listPlants = dao.getAllPlantsWithPaging(page, PAGE_SIZE);
                int totalPlants = dao.getTotalPlants();
                int totalPage = totalPlants / PAGE_SIZE;
                if (totalPlants % PAGE_SIZE != 0) {
                    totalPage += 1;
                }
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("listPlants", listPlants);
                session.setAttribute("urlHistory", "ViewAllController"
                                                 + ((pagenumber != null) ? ("?pagenumber=" + pagenumber) : ""));
                request.getRequestDispatcher("product.jsp").forward(request, response);
            } else {
                List<Plant> listPlants = dao.getPlantsByCategoryWithPagging(Integer.parseInt(category), page, PAGE_SIZE);
                if (!listPlants.isEmpty()) {
                    int totalPlants = dao.getTotalPlantsByCategory(Integer.parseInt(category));
                    int totalPage = totalPlants / PAGE_SIZE;
                    if (totalPlants % PAGE_SIZE != 0) {
                        totalPage += 1;
                    }
                    request.setAttribute("page", page);
                    request.setAttribute("totalPage", totalPage);
                    request.setAttribute("category", category);
                    request.setAttribute("listPlants", listPlants);
                    session.setAttribute("urlHistory", "ViewAllController?category=" + category
                                                 + ((pagenumber != null) ? ("&pagenumber=" + pagenumber) : ""));
                    request.getRequestDispatcher("product.jsp").forward(request, response);
                } else {
                    request.setAttribute("category", category);
                    request.setAttribute("NO_PRODUCT", "There are currently no products in this category");
                    session.setAttribute("urlHistory", "ViewAllController?category=" + category);
                    request.getRequestDispatcher("product.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            log("Error at ViewAllController: " + e);
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
