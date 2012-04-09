package Tietokantakasittely;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Kati
 */
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Kantayhteys {

    private String palvelin = "localhost";
    private int portti = 5432;
    // Mikä portti usersilla toimii ?
    //  private String tietokanta = "postgres";
    private String tietokanta = "asiakas";
    private String kayttajatunnus = "postgres";
    private String salasana = "tikas";
    private String ajuri = "org.postgresql.Driver";
    private String koodaus = "UTF-8";

    private Connection luoYhteys() throws SQLException {
        String osoite = "jdbc:postgresql://" + palvelin + ":" + portti + "/" + tietokanta;
        try {
            Class.forName(ajuri).newInstance();
        } catch (Exception e) {
            System.out.println("Virhe ajurin käyttöönotossa" + e.getMessage());
            e.printStackTrace();
        }
        return DriverManager.getConnection(osoite, kayttajatunnus, salasana);
    }

    public void tulostaTyotehtavat() throws SQLException {
        Connection yhteys = luoYhteys();

        Statement kysely = yhteys.createStatement();
        ResultSet tulosjoukko = kysely.executeQuery("select * from asiakas.tyotehtavat");

        System.out.println("tyotehtavat haettu");
        while (tulosjoukko.next()) {
            /*
             *
             * private int tyonumero; private int asiakasnumero; private char
             * tila; private String kuvaus; private String kadunnimi; private
             * String talonnumero; private String postinumero; private String
             * postitoimipaikka; private String asiakkaanyhteyshenkilo; private
             * String puhelinnumero; private String vastuuhenkilo; private Date
             * toivepvm;
             *
             *
             * tyonumero serial PRIMARY KEY, asiakasnumero int, tyolaji char
             * (3), -- KON,YLL,SUU,TOT tila char (1), -- N, K, L, P, V kuvaus
             * varchar (100), kadunnimi varchar (50), talonnumero varchar (10),
             * postinumero varchar (10), postitoimipaikka varchar (15),
             * asiakkaanyhteyshenkilo varchar (25), puhelinnumero varchar (15),
             * vastuuhenkilo varchar (25), toivepvm date,
             *
             *
             */
            int tyonumero = tulosjoukko.getInt("tyonumero");
            int asiakasnumero = tulosjoukko.getInt("asiakasnumero");
            String tyolaji = tulosjoukko.getString("tyolaji");
            String kuvaus = tulosjoukko.getString("kuvaus");
            String kadunnimi = tulosjoukko.getString("kadunnimi");
            String talonnumero = tulosjoukko.getString("talonnumero");
            String postinumero = tulosjoukko.getString("postinumero");
            String postitoimipaikka = tulosjoukko.getString("postitoimipaikka");
            String asiakkaanyhteyshenkilo = tulosjoukko.getString("asiakkaanyhteyshenkilo");
            String puhelinnumero = tulosjoukko.getString("puhelinnumero");
            String vastuuhenkilo = tulosjoukko.getString("vastuuhenkilo");
            Date toivepvm = tulosjoukko.getDate("toivepvm");

            System.out.println("Työnumero " + tyonumero + " " + kuvaus);

        }

        yhteys.close();
    }

    public List<Tyotehtava> haeAsiakkaanTyotehtavat(int asiakas) throws SQLException {
        System.out.println("haetaan asiakkaan työtehtävät, asiakas: " + asiakas);
        List<Tyotehtava> tyotehtavat = new ArrayList();
        Connection yhteys = luoYhteys();

        System.out.println("yhteys luotu");
        PreparedStatement valmisteltuKysely = yhteys.prepareStatement("select * from asiakas.tyotehtavat where asiakasnumero = ?");
        valmisteltuKysely.setInt(1, asiakas);
        ResultSet tulosjoukko = valmisteltuKysely.executeQuery();
//        Statement kysely = yhteys.createStatement();
//        ResultSet tulosjoukko = kysely.executeQuery("select * from asiakas.tyotehtavat");
//        
        while (tulosjoukko.next()) {
            int tyonumero = tulosjoukko.getInt("tyonumero");
            System.out.println("työnumero: " + tyonumero);
            int asiakasnumero = tulosjoukko.getInt("asiakasnumero");
            String tyolaji = tulosjoukko.getString("tyolaji");
            String kuvaus = tulosjoukko.getString("kuvaus");
            String kadunnimi = tulosjoukko.getString("kadunnimi");
            String talonnumero = tulosjoukko.getString("talonnumero");
            String postinumero = tulosjoukko.getString("postinumero");
            String postitoimipaikka = tulosjoukko.getString("postitoimipaikka");
            String asiakkaanyhteyshenkilo = tulosjoukko.getString("asiakkaanyhteyshenkilo");
            String puhelinnumero = tulosjoukko.getString("puhelinnumero");
            String vastuuhenkilo = tulosjoukko.getString("vastuuhenkilo");
            Date toivepvm = tulosjoukko.getDate("toivepvm");


            Tyotehtava t = new Tyotehtava(tyonumero, asiakasnumero, tyolaji,
                    kuvaus, kadunnimi, talonnumero, postinumero, postitoimipaikka,
                    asiakkaanyhteyshenkilo, puhelinnumero, vastuuhenkilo, toivepvm); // lisää parametrit
            tyotehtavat.add(t);
        }

        yhteys.close();
        return tyotehtavat;
    }

    public List<Tyotehtava> haeTyotehtavat() throws SQLException, UnsupportedEncodingException {
        List<Tyotehtava> tyotehtavat = new ArrayList();
        Connection yhteys = luoYhteys();

        System.out.println("yhteys luotu");

        Statement kysely = yhteys.createStatement();
        ResultSet tulosjoukko = kysely.executeQuery("select * from asiakas.tyotehtavat");

        while (tulosjoukko.next()) {
            int tyonumero = tulosjoukko.getInt("tyonumero");
            int asiakasnumero = tulosjoukko.getInt("asiakasnumero");
            String tyolaji = tulosjoukko.getString("tyolaji");

            String kuvaus = tulosjoukko.getString("kuvaus");
            kuvaus = URLDecoder.decode(kuvaus, koodaus);
            String kadunnimi = tulosjoukko.getString("kadunnimi");
            String talonnumero = tulosjoukko.getString("talonnumero");
            String postinumero = tulosjoukko.getString("postinumero");
            String postitoimipaikka = tulosjoukko.getString("postitoimipaikka");
            String asiakkaanyhteyshenkilo = tulosjoukko.getString("asiakkaanyhteyshenkilo");
            String puhelinnumero = tulosjoukko.getString("puhelinnumero");
            String vastuuhenkilo = tulosjoukko.getString("vastuuhenkilo");
            Date toivepvm = tulosjoukko.getDate("toivepvm");


            Tyotehtava t = new Tyotehtava(tyonumero, asiakasnumero, tyolaji,
                    kuvaus, kadunnimi, talonnumero, postinumero, postitoimipaikka,
                    asiakkaanyhteyshenkilo, puhelinnumero, vastuuhenkilo, toivepvm); // lisää parametrit
            tyotehtavat.add(t);
        }

        yhteys.close();
        return tyotehtavat;
    }

    public void lisaaTyotehtava(Tyotehtava tyotehtava) throws SQLException {
        System.out.println("lisataan");
        Connection yhteys = luoYhteys();

        Statement kysely = yhteys.createStatement();
        kysely.executeUpdate("insert into asiakas.tyotehtavat (asiakasnumero, kuvaus) "
                + "values (" + tyotehtava.getAsiakasnumero() + ", '" + tyotehtava.getKuvaus() + "')");

        yhteys.close();
    }

    public List<Asiakas> haeAsiakkaat() throws SQLException {
        List<Asiakas> asiakkaat = new ArrayList();
        Connection yhteys = luoYhteys();

        System.out.println("yhteys luotu");

        Statement kysely = yhteys.createStatement();
        ResultSet tulosjoukko = kysely.executeQuery("select * from asiakas.asiakkaat order by nimi");

        while (tulosjoukko.next()) {

            int asiakasnumero = tulosjoukko.getInt("asiakasnumero");
            String nimi = tulosjoukko.getString("nimi");
            String kadunnimi = tulosjoukko.getString("kadunnimi");
            String talonnumero = tulosjoukko.getString("talonnumero");
            String postinumero = tulosjoukko.getString("postinumero");
            String postitoimipaikka = tulosjoukko.getString("postitoimipaikka");
            String puhelinnumero = tulosjoukko.getString("puhelinnumero");
            String yhteyshenkilo = tulosjoukko.getString("yhteyshenkilo");
            Date asiakkaaksitulopvm = tulosjoukko.getDate("asiakkaaksitulopvm");
            String tila = tulosjoukko.getString("tila");

            Asiakas a = new Asiakas(asiakasnumero, nimi, kadunnimi, talonnumero,
                    postinumero, postitoimipaikka, puhelinnumero,
                    yhteyshenkilo, asiakkaaksitulopvm, tila);

            asiakkaat.add(a);
        }

        yhteys.close();
        return asiakkaat;
    }

    public Asiakas haeAsiakas(int asiakas) throws SQLException {
        Asiakas a = null;
        Connection yhteys = luoYhteys();

        System.out.println("yhteys luotu");

        PreparedStatement valmisteltuKysely = yhteys.prepareStatement("select * from asiakas.asiakkaat where asiakasnumero = ?");
        valmisteltuKysely.setInt(1, asiakas);
        ResultSet tulosjoukko = valmisteltuKysely.executeQuery();


        while (tulosjoukko.next()) {

            int asiakasnumero = tulosjoukko.getInt("asiakasnumero");
            String nimi = tulosjoukko.getString("nimi");
            String kadunnimi = tulosjoukko.getString("kadunnimi");
            String talonnumero = tulosjoukko.getString("talonnumero");
            String postinumero = tulosjoukko.getString("postinumero");
            String postitoimipaikka = tulosjoukko.getString("postitoimipaikka");
            String puhelinnumero = tulosjoukko.getString("puhelinnumero");
            String yhteyshenkilo = tulosjoukko.getString("yhteyshenkilo");
            Date asiakkaaksitulopvm = tulosjoukko.getDate("asiakkaaksitulopvm");
            String tila = tulosjoukko.getString("tila");

            a = new Asiakas(asiakasnumero, nimi, kadunnimi, talonnumero,
                    postinumero, postitoimipaikka, puhelinnumero,
                    yhteyshenkilo, asiakkaaksitulopvm, tila);


        }

        yhteys.close();
        return a;
    }

    public static void main(String[] args) throws SQLException, UnsupportedEncodingException {
        Kantayhteys k = new Kantayhteys();
        Connection yhteys = k.luoYhteys();

        System.out.println("Yhteys luotu");

        Tyotehtava uusi = new Tyotehtava();
        uusi.setAsiakasnumero(1);
        //uusi.setTyonumero(10); ei käytä tätä, vaan serial lisää yhden edellistä isomman
        uusi.setKuvaus("nyt on jo kiire");
        k.lisaaTyotehtava(uusi);

        for (Tyotehtava t : k.haeTyotehtavat()) {
            System.out.println(t.toString());
        }


        //k.tulostaViestit();
        yhteys.close();


    }
}
