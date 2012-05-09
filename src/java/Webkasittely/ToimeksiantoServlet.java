/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Webkasittely;

import Tietokantakasittely.Asiakas;
import Tietokantakasittely.Kantayhteys;
import Tietokantakasittely.Tyotehtava;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

        HttpSession session = request.getSession(false);
        if (session == null) { // käyttäjällä ei ole sessiota
            System.out.println("ei ole sessiota");
            response.setContentType("text/html;charset=UTF-8");
            response.sendRedirect("Login");
            //request.getRequestDispatcher("tyotehtavat.jsp").forward(request, response);
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

            // tarkista käyttäjätiedot
            // luo sessio
        }

        // testi usersia varten

        //request.getRequestDispatcher("\\:yhteys.jsp");
        // testi usersia varten
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
            throws ServletException, IOException, UnsupportedEncodingException {
        System.out.println("Toimeksianto: doPost");
        List<Tyotehtava> tyotehtavat = null;
        List<Asiakas> asiakkaat = null;

        Asiakas a;
        String erotin = "\\+";
        Boolean virhe = false;
        if (request.getParameter("siirryTunnit") != null) {
            System.out.println("Toimeksianto: siirryTunnit");
            response.setContentType("text/html;charset=UTF-8");
            response.sendRedirect(request.getContextPath() + "/Tyotunnit");

            response.flushBuffer();
            return;
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/tyotunnit.jsp");
//            dispatcher.forward(request, response);
//            return;
        }
        if (request.getParameter("haeAsiakas") != null) {
            String asiakas = (request.getParameter("asiakas"));
            System.out.println("asiakas: " + asiakas);
            request.setAttribute("asiakasnumero", asiakas);
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

            System.out.println("lisääTyö");
            Tyotehtava tyotehtava = new Tyotehtava();
            Date d = new Date();
            String asiakas = request.getParameter("asiakasnumero");
            //if (request.getParameter("asiakasnumero") != null)  {
            if (asiakas != null && asiakas.length() > 0) {

                //String asiakas = request.getParameter("asiakasnumero");
                System.out.println("asiakas: " + asiakas);
                String asiakasnumero = URLEncoder.encode(asiakas, koodaus);
                int asiakasnro = Integer.parseInt(asiakasnumero);
                tyotehtava.setAsiakasnumero(asiakasnro);

            }

            if (request.getParameter("tyolajivalinta") != null) {

                String tyolaji = URLEncoder.encode(request.getParameter("tyolajivalinta"), koodaus);

                tyotehtava.setTyolaji(tyolaji);

            } else {
                virhe = true;
                System.out.println("tyolajivalintaa ei valittu");
                request.setAttribute("virhe", "Valitse työlaji");
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/tyotehtavat.jsp");
//                dispatcher.forward(request, response);
            }

            if (request.getParameter("tyotilavalinta") != null) {

                String tyotila = URLEncoder.encode(request.getParameter("tyotilavalinta"), koodaus);

                tyotehtava.setTila(tyotila);

            } else {
                virhe = true;
                System.out.println("tyotila ei valittu");
                request.setAttribute("virhe", "Valitse työn kiireellisyys");
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/tyotehtavat.jsp");
//                dispatcher.forward(request, response);
            }


            String kuvaus = request.getParameter("kuvaus");
            if (kuvaus != null && !kuvaus.contentEquals(" ")) {
                //if (request.getParameter("kuvaus") != null) {
                System.out.println("kuvaus: " + kuvaus);
                kuvaus = URLEncoder.encode(request.getParameter("kuvaus"), koodaus);

                tyotehtava.setKuvaus(kuvaus);

            } else {
                virhe = true;
                System.out.println("Kuvaus puuttuu");
                request.setAttribute("virhe", "Kirjoita työn kuvaus");
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/tyotehtavat.jsp");
//                dispatcher.forward(request, response);
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
            if (request.getParameter("puhelin") != null) {
                String puhelinnumero = URLEncoder.encode(request.getParameter("puhelin"), koodaus);

                tyotehtava.setPuhelinnumero(puhelinnumero);
            }
            String toivepaiva = request.getParameter("toivepaiva");
            if (toivepaiva != null && toivepaiva.length() > 7) {
                //(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)
                if (toivepaiva.matches("(0?[1-9]|[12][0-9]|3[01])(0?[1-9]|1[012])((19|20)\\d\\d)")) {
                    String pv = toivepaiva.substring(0, 2);
                    String kk = toivepaiva.substring(2, 4);
                    String vv = toivepaiva.substring(6, 8);
                    String paiva = pv + "/" + kk + "/" + vv;
                    System.out.println("päivä: " + paiva);
//                    Date p = new Date();
//                    DateFormat formatter = new DateFormat();
//                    String format = DateFormat.getInstance().format(paiva);
                    tyotehtava.setToivepvm(paiva);

                } else {
                    virhe = true;
                    request.setAttribute("virhe", "Virheellinen päivämäärä, muoto");

//                    RequestDispatcher dispatcher = request.getRequestDispatcher("/tyotehtavat.jsp");
//                    dispatcher.forward(request, response);
//                    return;
                }

            } else {
                if (!virhe) {
                    virhe = true;
                    request.setAttribute("virhe", "Virheellinen päivämäärä, pituus");

                }

            }
            if (virhe) {
                try {
                    asiakkaat = k.haeAsiakkaat();
                    request.setAttribute("asiakkaat", asiakkaat);
                } catch (SQLException ex) {
                    Logger.getLogger(ToimeksiantoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                asiakas = request.getParameter("asiakasnumero");
                request.setAttribute("anro", asiakas);
                String nimi = request.getParameter("nimi");
                request.setAttribute("animi", nimi);
                String osoite = request.getParameter("katuosoite");
                request.setAttribute("akatu", osoite);
                String postiosoite = request.getParameter("postiosoite");

                request.setAttribute("aposti", postiosoite);

                String yhteyshenkilo = request.getParameter("yhteyshenkilo");
                request.setAttribute("ayhteyshlo", yhteyshenkilo);
                String puhelin = request.getParameter("puhelin");
                request.setAttribute("apuhelin", puhelin);
                String paiva = request.getParameter("toivepaiva");
                request.setAttribute("paiva", paiva);
                String tehtavakuvaus = request.getParameter("kuvaus");
                request.setAttribute("tehtavakuvaus", tehtavakuvaus);
                //<textarea rows="10" cols="57" name ="vkuvaus" value="${tehtavakuvaus}"> </textarea>
                int asnro = Integer.parseInt(asiakas);
                try {
                    tyotehtavat = k.haeAsiakkaanTyotehtavat(asnro);
                    request.setAttribute("tyotehtavat", tyotehtavat);
                } catch (SQLException ex) {
                    Logger.getLogger(ToimeksiantoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }


                response.setContentType("text/html;charset=UTF-8");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/tyotehtavat.jsp");
                dispatcher.forward(request, response);
                return;
            }
            HttpSession session = request.getSession(false);
            if (session != null) { // käyttäjällä ei ole sessiota
                String kayttaja = (String) session.getAttribute("ktunnus");
                String vastuuhenkilo;
                try {
                    vastuuhenkilo = k.haeTyontekija(kayttaja);
                    tyotehtava.setVastuuhenkilo(vastuuhenkilo);
                } catch (SQLException ex) {
                    Logger.getLogger(ToimeksiantoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    if (!virhe) {
                        System.out.println("k.lisaaTyotehtava");
                        k.lisaaTyotehtava(tyotehtava);
                    }

                } catch (SQLException e) {
                    throw new ServletException(e);
                }
            }
            //if (request.getParameter("asiakasnumero") != null) {
            if (asiakas != null && asiakas.length() > 0) //String asiakas = request.getParameter("asiakasnumero");
            {
                System.out.println("lisauksen jalk. asiakasumero: " + asiakas);
            }
            String asiakasnumero = URLEncoder.encode(asiakas, koodaus);
            int asiakasnro = Integer.parseInt(asiakasnumero);
            try {
                a = k.haeAsiakas(asiakasnro);
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
                tyotehtavat = k.haeAsiakkaanTyotehtavat(asiakasnro);
                request.setAttribute("tyotehtavat", tyotehtavat);
            } catch (SQLException ex) {
                Logger.getLogger(ToimeksiantoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        try {
            asiakkaat = k.haeAsiakkaat();
            request.setAttribute("asiakkaat", asiakkaat);
        } catch (SQLException ex) {
            Logger.getLogger(ToimeksiantoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/tyotehtavat.jsp");
        dispatcher.forward(request, response);
        return;
    }
//    private void asetaAnnetutTiedot(request) {
//        String asiakas = request.getParameter("asiakasnumero");
//        request.setAttribute("anro", asiakas);
//        String nimi = request.getParameter("animi");
//        request.setAttribute("animi", nimi);
//        String osoite = request.getParameter("katuosoite");
//        request.setAttribute("akatu", osoite);
//        String postiosoite = request.getParameter("postiosoite");
//
//        request.setAttribute("aposti", postiosoite);
//
//        String yhteyshenkilo = request.getParameter("yhteyshenkilo");
//        request.setAttribute("ayhteyshlo", yhteyshenkilo);
//        String puhelin = request.getParameter("puhelin");
//        request.setAttribute("apuhelin", puhelin);
//        int asnro = Integer.parseInt(asiakasnumero);
//        tyotehtavat = k.haeAsiakkaanTyotehtavat(asnro);
//
//        request.setAttribute("tyotehtavat", tyotehtavat);
//
//    }
}
//                <tr> <td> Asiakkaan kaikki työtehtävät </td> </tr>
//                <tr onmouseup= 'alert ("hiiri alas")'>
//                    <td width="100" rowspan="6" colspan="4">
//                        <font face="Courier">
//                        <c:forEach items="${tyotehtavat}" var="tyotehtava">
//
//                            <ol>
//                                <li> ${tyotehtava.tyonumero} </li>
//                                <li> ${tyotehtava.tila} </li>
//                                <li> ${tyotehtava.tyolaji} </li>
//                                <li> ${tyotehtava.kuvaus} </li>
//                                <li> ${tyotehtava.kadunnimi} </li>
//                                <li> ${tyotehtava.talonnumero} </li>
//                                <li> ${tyotehtava.postinumero} </li>
//                                <li> ${tyotehtava.postitoimipaikka} </li>
//                                <li> ${tyotehtava.asiakkaanyhteyshenkilo} </li>
//                                <li> ${tyotehtava.puhelinnumero} </li>
//                                <li> ${tyotehtava.vastuuhenkilo} </li>
//                                <li> ${tyotehtava.toivepvm} </li> <br>
//                            </ol>
//                        </c:forEach>
//                        </font>
//                    </td>
//                </tr>
//            </table>  
