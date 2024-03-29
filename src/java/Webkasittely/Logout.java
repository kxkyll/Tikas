/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Webkasittely;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kati
 */
public class Logout extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
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
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            RequestDispatcher dispatcher = request.getRequestDispatcher("logout.jsp");
            dispatcher.forward(request, response);

//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Logout</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Logout at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //   processRequest(request, response);
        System.out.println("logout doget");
        request.setCharacterEncoding("UTF8");
        HttpSession session = request.getSession(false);
        if (session != null) { // lopetetaan sessio jos sellainen on
            System.out.println("sessio on ja se lopetetaan");
            session.invalidate();
        }
        //processRequest(request, response);

        request.setAttribute("viesti", "Olet kirjautunut ulos");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logout.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("logout dopost");
        request.setCharacterEncoding("UTF8");
        if (request.getParameter("siirryLogin") != null) {
            System.out.println("siirryLogin painettu");


//        HttpSession session = request.getSession(false);
//        if (session != null) { // lopetetaan sessio jos sellainen on
//            session.invalidate();
//        }
//        //processRequest(request, response);

            request.setAttribute("viesti", "Anna käyttäjätunnus ja salasana");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
//            dispatcher.forward(request, response);
            response.sendRedirect(request.getContextPath() + "/Login");

            response.flushBuffer();
            return;
        }
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
