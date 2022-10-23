package com.khoahd7621.controller.sync;

import com.khoahd7621.util.SecurityUtils;
import com.khoahd7621.util.Tools;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author KhoaHD7621
 */
@SuppressWarnings("SameReturnValue")
public class LoginController extends HttpServlet {

    private static final String INVALID = "invalid.jsp";
    private static final String HOME = "HomeController";
    private static final String LOGIN = "login.jsp";
    private static final String ADMIN_PAGE = "AdminHomeController";
    private static final int ADMIN = 1;
    private static final String USER_PAGE = "UserHomeController";
    private static final int USER = 0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        String url = INVALID;
        try {
            Cookie[] c = request.getCookies();
            AccountDAO dao = new AccountDAO();
            HttpSession session = request.getSession();
            String token = "";
            if (c != null) {
                for (Cookie aCookie : c) {
                    if (aCookie.getName().equals("selector")) {
                        token = aCookie.getValue();
                    }
                }
            }
            if (!token.equals("")) {
                if (dao.validToken(token)) {
                    Account account = dao.getAccount(token);
                    int role = account.getRole();
                    session.setAttribute("LOGIN_USER", account);
                    if (role == 0) {
                        // Load cart in Cookie of this user to server if exists
                        StringBuilder cartStr = new StringBuilder();
                        for (Cookie Cooky : c) {
                            if (Cooky.getName().equals("cart")) {
                                cartStr.append(Cooky.getValue());
                            }
                        }
                        PlantDAO plantDAO = new PlantDAO();
                        Map<Integer, Cart> carts = new LinkedHashMap<>();
                        // If you get the cart string from the cookie,
                        // convert this string to a cart and save it to the session
                        if (!cartStr.toString().equals("")) {
                            // Format cart in cookie: pId1:quantity1-pId2:quantity2-pId3:quantity3-...
                            String[] s = cartStr.toString().split("-");
                            for (String i : s) {
                                // Format each element: pId:quantity
                                String[] n = i.split(":");
                                int pid = Integer.parseInt(n[0]);
                                int quantity = Integer.parseInt(n[1]);
                                Plant plant = plantDAO.getPlant(pid);
                                carts.put(pid, new Cart(plant, quantity));
                            }
                            session.setAttribute("carts", carts);
                        }
                        session.setAttribute("destPage", "home");
                        url = HOME;
                    } else if (role == 1) {
                        session.setAttribute("destPage", "admin");
                        url = ADMIN_PAGE;
                    }
                } else {
                    session.setAttribute("destPage", "home");
                    url = HOME;
                }
            } else {
                String email = request.getParameter("email");
                if (email == null) {
                    session.setAttribute("destPage", "home");
                    url = HOME;
                } else {
                    String password = SecurityUtils.hashMd5(request.getParameter("password"));
                    System.out.println(password);
                    String remember = request.getParameter("remember");

                    Account account = dao.getAccount(email, password);
                    if (account != null) {
                        if (account.getStatus() == 0) {
                            request.setAttribute("ERROR_MASSEGE", "Your account has been locked! Please contact admin to get it unlocked!");
                            url = LOGIN;
                        } else {
                            session.setAttribute("LOGIN_USER", account);
                            if (ADMIN == account.getRole()) {
                                session.setAttribute("destPage", "admin");
                                url = ADMIN_PAGE;
                            } else if (USER == account.getRole()) {

                                // If user from Checkout page to here to checkout 
                                // => send response back to CheckoutController to show response.jsp
                                String destPage = (String) session.getAttribute("destPage");
                                if (destPage != null && destPage.equals("checkOut")) {
                                    response.sendRedirect("CheckOutController");
                                    return;
                                } else {
                                    // Else set url to User page
                                    session.setAttribute("destPage", "user");
                                    url = USER_PAGE;
                                }
                            } else {
                                request.setAttribute("ERROR_MASSEGE", "Your role is not support!");
                            }

                            // Create and save token to cookies if user click "remember me"
                            if (remember != null) {
                                token = Tools.generateNewToken();
                                dao.updateToken(token, email);
                                Cookie cookie = new Cookie("selector", token);
                                cookie.setMaxAge(60 * 5);
                                response.addCookie(cookie);
                            }
                        }
                    } else {
                        url = LOGIN;
                        request.setAttribute("email", email);
                        request.setAttribute("ERROR_MASSEGE", "Incorrect email or password!");
                    }
                }
            }
        } catch (Exception e) {
            log("Error at LoginController: " + e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
