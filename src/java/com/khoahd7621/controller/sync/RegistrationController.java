package com.khoahd7621.controller.sync;

import com.khoahd7621.util.SecurityUtils;
import java.io.IOException;
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
public class RegistrationController extends HttpServlet {

// --Commented out by Inspection START (10/23/2022 9:00 PM):
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException {
//        try {
//            HttpSession session = request.getSession();
//            Account acc = (Account) session.getAttribute("LOGIN_USER");
//            if (acc == null) {
//                response.sendRedirect("registration.jsp");
//            } else {
//                if (acc.getRole() == 0) {
//                    response.sendRedirect("user.jsp");
//                } else if (acc.getRole() == 1) {
//                    response.sendRedirect("admin.jsp");
//                } else {
//                    response.sendRedirect("HomeController");
//                }
//            }
//
// --Commented out by Inspection START (10/23/2022 9:00 PM):
////        } catch (Exception e) {
////            log("Error at RegistrationController: " + e);
////        }
////    }
//// --Commented out by Inspection STOP (10/23/2022 9:00 PM)
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException {
//        try {
//            String email = request.getParameter("email");
//
//            AccountDAO dao = new AccountDAO();
//            Account account = dao.getAccountInfoByEmail(email);
//            if (account != null) {
//                request.setAttribute("MSG_ERROR", "The account already exists in the system! Please use this email to login.");
//                request.setAttribute("email", email);
//                request.getRequestDispatcher("registration.jsp").forward(request, response);
//            } else {
//                String name = request.getParameter("name");
//                String phone = request.getParameter("phone");
//                String password = SecurityUtils.hashMd5(request.getParameter("password"));
//                boolean check = dao.insertAccount(email, password, name, phone, 1, 0);
//                if (check) {
//                    request.setAttribute("MSG_SUCCESS", "You have successfully registered an account!");
//                    request.getRequestDispatcher("login.jsp").forward(request, response);
// --Commented out by Inspection STOP (10/23/2022 9:00 PM)
                }
            }
        } catch (Exception e) {
            log("Error at RegistrationController: " + e);
        }
    }

}
