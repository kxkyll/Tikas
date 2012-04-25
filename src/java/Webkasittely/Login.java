/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Webkasittely;

import Tietokantakasittely.Kantayhteys;
import Tietokantakasittely.Kayttaja;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Login extends HttpServlet {

    private Kantayhteys k;

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
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);

//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Login</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);

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
        //processRequest(request, response);

        System.out.println("Login doPost");


        String ktunnus = request.getParameter("ktunnus");
        System.out.println("ktunnus: " + ktunnus);
        String salasana = request.getParameter("salasana");
        System.out.println("salasana " + salasana);

        if (ktunnus != null && ktunnus.length() > 5 && ktunnus.length() < 9) {
            if (salasana != null && salasana.length() > 8 && salasana.length() < 17) {

                System.out.println("tunnarien pituudet kunnossa");
                k = new Kantayhteys();
                try {
                    Kayttaja kirjautuja = k.haeKayttajatiedot(ktunnus);
                    if (salasana.equals(kirjautuja.getSalasana())) {
                        HttpSession session = request.getSession();
                        session.setAttribute("ktunnus", ktunnus);
                        //session.setAttribute("salasana", salasana);

                        response.sendRedirect(request.getContextPath() + "/Toimeksianto");

                        response.flushBuffer();
                        return;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println("salasana ei täsmää");
                request.setAttribute("virhe", "Virheellinen käyttäjätunnus tai salasana");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                //response.sendRedirect(request.getContextPath() + "/Login");

            } else {
                System.out.println("salasana ei oikean mittainen");
                request.setAttribute("virhe", "Virheellinen käyttäjätunnus tai salasana");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                //response.sendRedirect(request.getContextPath() + "/Login");
            }
        } else {
            System.out.println("kayttajatunnus ei oikean mittainen");
            request.setAttribute("virhe", "Virheellinen käyttäjätunnus tai salasana");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
            //response.sendRedirect(request.getContextPath() + "/Login");
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
