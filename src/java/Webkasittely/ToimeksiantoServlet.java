/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Webkasittely;

import Tietokantakasittely.Asiakas;
import Tietokantakasittely.Kantayhteys;
import Tietokantakasittely.Tyotehtava;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToimeksiantoServlet extends HttpServlet {

    String koodaus = "UTF-8";
    private Kantayhteys k = new Kantayhteys();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doGet");
        List<Tyotehtava> tyotehtavat = null;
        List<Asiakas> asiakkaat = null;
        Asiakas a = null;

        try {

            asiakkaat = k.haeAsiakkaat();
            request.setAttribute("asiakkaat", asiakkaat);



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
        String erotin = "\\+";

        if (request.getParameter("haeAsiakas") != null) {
            String asiakas = (request.getParameter("asiakas"));
            System.out.println("asiakas: " + asiakas);

            if (asiakas != null) {

                int asnro = Integer.parseInt(asiakas);
                System.out.println("asnro: " + asnro);
                try {
                    a = k.haeAsiakas(asnro);

                    request.setAttribute("anro", a.getAsiakasnumero());
                    String nimi = URLDecoder.decode(a.getNimi(), koodaus);
                    request.setAttribute("animi", nimi);
                    String katuosoite = URLDecoder.decode(a.getKadunnimi(), koodaus);
                    String talonumero = URLDecoder.decode(a.getTalonnumero(), koodaus);
                    request.setAttribute("akatu", katuosoite + " " + talonumero);
                    String postinumero = URLDecoder.decode(a.getPostinumero(), koodaus);
                    String postitoimipaikka = URLDecoder.decode(a.getPostitoimipaikka(), koodaus);
                    request.setAttribute("aposti", postinumero + " " + postitoimipaikka);
                    String yhteyshenkilo = URLDecoder.decode(a.getYhteyshenkilo(), koodaus);
                    request.setAttribute("ayhteyshlo", yhteyshenkilo);
                    String puhelin = URLDecoder.decode(a.getPuhelinnumero(), koodaus);
                    request.setAttribute("apuhelin", puhelin);
                    tyotehtavat = k.haeAsiakkaanTyotehtavat(asnro);

                    request.setAttribute("tyotehtavat", tyotehtavat);
                } catch (SQLException ex) {
                    Logger.getLogger(ToimeksiantoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } else if (request.getParameter("lisaaTyo") != null) {

            Tyotehtava tyotehtava = new Tyotehtava();
            Date d = new Date();


            if (request.getParameter("asiakasnumero") != null) {
                String asiakas = request.getParameter("asiakasnumero");
                System.out.println("asiakas: " + asiakas);
                String asiakasnumero = URLEncoder.encode(asiakas, koodaus);
                int asiakasnro = Integer.parseInt(asiakasnumero);
                tyotehtava.setAsiakasnumero(asiakasnro);
            }

            if (request.getParameter("tyolajivalinta") != null) {

                String tyolaji = URLEncoder.encode(request.getParameter("tyolajivalinta"), koodaus);

                tyotehtava.setTyolaji(tyolaji);

            }
            if (request.getParameter("tyotilavalinta") != null) {

                String tyotila = URLEncoder.encode(request.getParameter("tyotilavalinta"), koodaus);

                tyotehtava.setKuvaus(tyotila);

            }



            if (request.getParameter("kuvaus") != null) {

                String kuvaus = URLEncoder.encode(request.getParameter("kuvaus"), koodaus);

                tyotehtava.setKuvaus(kuvaus);

            }
            if (request.getParameter("katuosoite") != null) {
                String katuosoite = URLEncoder.encode(request.getParameter("katuosoite"), koodaus);
                System.out.println("katuosoite endoocauksen jälkeen: " + katuosoite);
                String katuosoitetaulu[] = katuosoite.split(erotin, 2);

                System.out.println("katuosoitetaulun pituus: " + katuosoitetaulu.length);
                for (String k : katuosoitetaulu) {
                    System.out.println(k);
                }

                if (katuosoitetaulu.length > 1) {
                    tyotehtava.setKadunnimi(katuosoitetaulu[0]);
                    tyotehtava.setTalonnumero(katuosoitetaulu[1]);
                } else {
                    tyotehtava.setKadunnimi(katuosoitetaulu[0]);
                }

            }
            if (request.getParameter("postiosoite") != null) {
                String postiosoite = URLEncoder.encode(request.getParameter("postiosoite"), koodaus);

                String postiosoitetaulu[] = postiosoite.split(erotin, 2);
                if (postiosoitetaulu.length > 1) {
                    tyotehtava.setPostinumero(postiosoitetaulu[0]);
                    tyotehtava.setPostitoimipaikka(postiosoitetaulu[1]);
                } else {
                    tyotehtava.setPostinumero(postiosoitetaulu[0]);
                }

            }
            if (request.getParameter("yhteyshenkilo") != null) {
                String yhteyshenkilo = URLEncoder.encode(request.getParameter("yhteyshenkilo"), koodaus);

                tyotehtava.setAsiakkaanyhteyshenkilo(yhteyshenkilo);
            }
            if (request.getParameter("puhelinnumero") != null) {
                String puhelinnumero = URLEncoder.encode(request.getParameter("puhelinnumero"), koodaus);

                tyotehtava.setPuhelinnumero(puhelinnumero);
            }
            String toivepaiva = request.getParameter("toivepaiva");
            if (toivepaiva != null) {

                int toivottu = Integer.parseInt(toivepaiva);
                // todo: päivämäärän asettaminen puuttuu

            }

//            kuvaus = kuvaus.replace("<", "&lt;");
//            kuvaus = kuvaus.replace(">", "&gt;");


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
