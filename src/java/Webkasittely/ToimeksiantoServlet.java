/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Webkasittely;

import Tietokantakasittely.Asiakas;
import Tietokantakasittely.Kantayhteys;
import Tietokantakasittely.Tyotehtava;
import java.beans.Encoder;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

public class ToimeksiantoServlet extends HttpServlet {

    private Kantayhteys k = new Kantayhteys();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doGet");
        List<Tyotehtava> tyotehtavat = null;
        List<Asiakas> asiakkaat = null;
        Asiakas a = null;

        try {
            if (request.getParameter("haeAsiakas") != null) {
                String asiakas = (request.getParameter("asiakas"));
                System.out.println("asiakas: " + asiakas);

                if (asiakas != null) {

                    int asnro = Integer.parseInt(asiakas);
                    System.out.println("asnro: " + asnro);
                    a = k.haeAsiakas(asnro);


                    System.out.println("asiakas" + a.getAsiakasnumero() + " " + a.getNimi());
                    request.setAttribute("anro", a.getAsiakasnumero());
                    request.setAttribute("animi", a.getNimi());
                    request.setAttribute("akatu", a.getKadunnimi() + " " + a.getTalonnumero());
                    request.setAttribute("aposti", a.getPostinumero() + " " + a.getPostitoimipaikka());
                    request.setAttribute("ayhteyshlo", a.getYhteyshenkilo());
                    request.setAttribute("apuhelin", a.getPuhelinnumero());
                    tyotehtavat = k.haeAsiakkaanTyotehtavat(asnro);

                    request.setAttribute("tyotehtavat", tyotehtavat);
                }
            } else if (request.getParameter("lisaaTyo") != null) {
            } else {

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
        List<Tyotehtava> tyotehtavat = null;
        List<Asiakas> asiakkaat = null;
        Asiakas a;

        if (request.getParameter("haeAsiakas") != null) {
            String asiakas = (request.getParameter("asiakas"));
            System.out.println("asiakas: " + asiakas);

            if (asiakas != null) {

                int asnro = Integer.parseInt(asiakas);
                System.out.println("asnro: " + asnro);
                try {
                    a = k.haeAsiakas(asnro);
                    request.setAttribute("anro", a.getAsiakasnumero());
                    request.setAttribute("animi", a.getNimi());
                    request.setAttribute("akatu", a.getKadunnimi() + " " + a.getTalonnumero());
                    request.setAttribute("aposti", a.getPostinumero() + " " + a.getPostitoimipaikka());
                    request.setAttribute("ayhteyshlo", a.getYhteyshenkilo());
                    request.setAttribute("apuhelin", a.getPuhelinnumero());
                    tyotehtavat = k.haeAsiakkaanTyotehtavat(asnro);

                    request.setAttribute("tyotehtavat", tyotehtavat);
                } catch (SQLException ex) {
                    Logger.getLogger(ToimeksiantoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } else if (request.getParameter("lisaaTyo") != null) {
            Tyotehtava tyotehtava = new Tyotehtava();
//            asiakasnimi
//            anro
//            katuosoite
//            postiosoite
//            yhteyshenkilo
//            puhelin

            //tyolajivalinta
            //tyotilavalinta
            //toivepaiva
            //kuvaus
//            if (request.getParameter("asiakasnimi") != null) {
//                String asiakasnimi = URLEncoder.encode(request.getParameter("asiakasnimi"), "UTF-8");
//                tyotehtava.setAsiakasnimi(asiakasnimi);
//            }
                if (request.getParameter("anro") != null) {
                String anro = URLEncoder.encode(request.getParameter("anro"),"UTR-8");
                int asiakasnumero = Integer.parseInt(anro);
                tyotehtava.setAsiakasnumero(asiakasnumero);
                }
                  if (request.getParameter("kuvaus") != null) {
                    String kuvaus = URLEncoder.encode(request.getParameter("kuvaus"), "UTF-8");
                    tyotehtava.setKuvaus(kuvaus);
                if (request.getParameter("katuosoite") != null) {
                    String osoite = URLEncoder.encode(request.getParameter("katuosoite"), "UTF-8");
 
                    String osoitetaulu [] = osoite.split(osoite);
                    tyotehtava.setKuvaus(kuvaus);
//            kuvaus = kuvaus.replace("<", "&lt;");
//            kuvaus = kuvaus.replace(">", "&gt;");
                    System.out.println("kuvaus");
                }

                    tyotehtava.setAsiakasnumero(1);


                    try {
                        System.out.println("k.lisaaTyotehtava");
                        k.lisaaTyotehtava(tyotehtava);
                    } catch (SQLException e) {
                        throw new ServletException(e);
                    }
                }

            }

            doGet(request, response);
        }
    }
