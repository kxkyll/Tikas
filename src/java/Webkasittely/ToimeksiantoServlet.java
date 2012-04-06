/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Webkasittely;

import Tietokantakasittely.Asiakas;
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
        List<Asiakas> asiakkaat = null;

        try {
            if (request.getParameter("haeAsiakas") != null) {
                String asiakasnumero = (request.getParameter("asiakasnumero"));
                
                if (asiakasnumero != null) {
                    
                    int asnro = Integer.parseInt(asiakasnumero);
                    asiakkaat = k.haeAsiakas(asnro);
                    
                    Asiakas a = asiakkaat.get(0);
                    System.out.println("asiakas" +a.getAsiakasnumero() +" " +a.getNimi());
                    request.setAttribute("anro", a.getAsiakasnumero());
                    request.setAttribute("animi", a.getNimi());
                    request.setAttribute("akatu", a.getKadunnimi()+ " "+a.getTalonnumero());
                    request.setAttribute("aposti", a.getPostinumero()+ " "+a.getPostitoimipaikka());
                    request.setAttribute("ayhteyshlo", a.getYhteyshenkilo());
                    request.setAttribute("apuhelin", a.getPuhelinnumero());
                    tyotehtavat = k.haeAsiakkaanTyotehtavat(asnro);
                    asiakkaat.clear();
                    request.setAttribute("tyotehtavat", tyotehtavat);
                                    }
            }else {
                
                asiakkaat = k.haeAsiakkaat();
                request.setAttribute("asiakkaat", asiakkaat);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        response.setContentType("text/html;charset=UTF-8");
        
        
        
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
