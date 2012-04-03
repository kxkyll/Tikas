/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Webkasittely;

import Tietokantakasittely.Kantayhteys;
import Tietokantakasittely.Tyotehtava;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToimeksiantoServlet extends HttpServlet {

    private Kantayhteys k = new Kantayhteys();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doGet");
        List<Tyotehtava> tyotehtavat = null;

        try {
            tyotehtavat = k.haeTyotehtavat();
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("tyotehtavat", tyotehtavat);
        request.getRequestDispatcher("tyotehtavat.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doPost");
        if (request.getParameter("kuvaus") != null) {
            String kuvaus = request.getParameter("kuvaus");
            kuvaus = kuvaus.replace("<", "&lt;");
            kuvaus = kuvaus.replace(">", "&gt;");
            System.out.println("kuvaus");
            Tyotehtava tyotehtava = new Tyotehtava();
            tyotehtava.setAsiakasnumero(1);
            tyotehtava.setKuvaus(kuvaus);

            try {
                System.out.println("k.lisaaTyotehtava");
                k.lisaaTyotehtava(tyotehtava);
            } catch (SQLException e) {
                throw new ServletException(e);
            }
        }

        doGet(request, response);
    }
}
