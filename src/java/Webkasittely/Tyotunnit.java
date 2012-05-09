/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Webkasittely;

import Tietokantakasittely.Asiakas;
import Tietokantakasittely.Kantayhteys;
import Tietokantakasittely.Tyotehtava;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.List;
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
public class Tyotunnit extends HttpServlet {

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
    String koodaus = "UTF-8";
    private Kantayhteys k = new Kantayhteys();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Tyotunnit processRequest");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Tyotunnit</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Tyotunnit at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        System.out.println("Tyotunnit doGet");
        //processRequest(request, response);

        List<Tyotehtava> tyotehtavat = null;
        List<Asiakas> asiakkaat = null;
        Asiakas a = null;

        HttpSession session = request.getSession(false);
        if (session == null) { // käyttäjällä ei ole sessiota
            System.out.println("ei ole sessiota");
            response.setContentType("text/html;charset=UTF-8");
            response.sendRedirect("Login");

            return;

        } else {
            System.out.println("Sessio on olemassa");
            if ((session.getAttribute("ktunnus") != null || session.getAttribute("salasana") != null)) {
                String kayttaja = (String) session.getAttribute("ktunnus");
                request.setAttribute("ktunnus", kayttaja);
                System.out.println("tunnukset sessiolla");
            } else {
                System.out.println("kayttaja ei ole antanut käyttäjätunnusta "
                        + "tai salasanaa, tai ne ovat kadonneet sessiosta");
                response.setContentType("text/html;charset=UTF-8");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;

            }

        }

        try {

            asiakkaat = k.haeAsiakkaat();
            request.setAttribute("asiakkaat", asiakkaat);

        } catch (SQLException e) {
            throw new ServletException(e);
        }

        response.setContentType("text/html;charset=UTF-8");

        request.getRequestDispatcher("tyotunnit.jsp").forward(request, response);
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
            throws ServletException, IOException, UnsupportedEncodingException {
        System.out.println("Tyotunnit doPost");
        //    processRequest(request, response);
        List<Tyotehtava> tyotehtavat = null;
        List<Asiakas> asiakkaat = null;

        Asiakas a;
        Tyotehtava t;
        String erotin = "\\+";
        Boolean virhe = false;
        if (request.getParameter("siirryTehtavat") != null) {
            System.out.println("Tyotunnit: siirryTehtavat");
            response.setContentType("text/html;charset=UTF-8");
            response.sendRedirect(request.getContextPath() + "/Toimeksianto");

            response.flushBuffer();
            return;
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/tyotunnit.jsp");
//            dispatcher.forward(request, response);
//            return;
        }
        if (request.getParameter("haeAsiakkaanTehtavat") != null) {
            String asiakas = (request.getParameter("asiakas"));
            System.out.println("asiakas: " + asiakas);
            request.setAttribute("asiakasnumero", asiakas);


            if (asiakas != null) {

                int asnro = Integer.parseInt(asiakas);
                System.out.println("asnro: " + asnro);
                try {

                    tyotehtavat = k.haeAsiakkaanTyotehtavat(asnro);
                    String animi = k.haeAsiakkaanNimi(asnro);
                    request.setAttribute("animi", animi);

                    request.setAttribute("tyotehtavat", tyotehtavat);
                } catch (SQLException ex) {
                    Logger.getLogger(ToimeksiantoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }


            }
        }

        if (request.getParameter("haeTehtavanTiedot") != null) {
            String tehtava = (request.getParameter("tehtava"));
            System.out.println("tehtava: " + tehtava);
            request.setAttribute("tehtavanumero", tehtava);


            if (tehtava != null) {
                String asiakas = (request.getParameter("asiakasnumero"));
                System.out.println("asiakasnumero: " +asiakas);
                //int asiakasnumero = Integer.parseInt(asiakas);
                int tehtavanro = Integer.parseInt(tehtava);
                System.out.println("tehtavanro: " + tehtavanro);
                try {
                    t = k.haeTyotehtava(tehtavanro);
                    request.setAttribute("tehtavanTiedot", t);
                  //  tyotehtavat = k.haeAsiakkaanTyotehtavat(asiakasnumero);
                  //  String animi = k.haeAsiakkaanNimi(asiakasnumero);
                  //  request.setAttribute("animi", animi);

                    request.setAttribute("tyotehtavat", tyotehtavat);
                } catch (SQLException ex) {
                    Logger.getLogger(Tyotunnit.class.getName()).log(Level.SEVERE, null, ex);
                }


            }
        }
        try {

            asiakkaat = k.haeAsiakkaat();
            request.setAttribute("asiakkaat", asiakkaat);

        } catch (SQLException e) {
            throw new ServletException(e);
        }
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/tyotunnit.jsp");
        dispatcher.forward(request, response);
        return;


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
