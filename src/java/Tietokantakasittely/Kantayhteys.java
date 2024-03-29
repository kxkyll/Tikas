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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Kantayhteys {

    private Boolean koti = true;
    private String palvelin = "localhost";
    private int portti = 5432;
    private String ajuri = "org.postgresql.Driver";
    private String koodaus = "UTF-8";
    // Mikä portti usersilla toimii ?
    //  private String tietokanta = "postgres";
    private String tietokanta = "asiakas";
    private String userstietokanta = "kxkyllon";
    private String kayttajatunnus = "postgres";
    private String userskayttajatunnus = "kxkyllon";
    private String salasana = "tikas";
    private String userssalasana = "92108a56194e7617";
    private String osoite;

    private Connection luoYhteys() throws SQLException {
        System.out.println("testaustulostus");
        if (koti) {
            osoite = "jdbc:postgresql://" + palvelin + ":" + portti + "/" + tietokanta;
        } else {
            // servlet ohjeesta
//                conn = createConnection("org.postgresql.Driver",
//             "jdbc:postgresql://localhost/username",
//             "username","password");
//            

            osoite = "jdbc:postgresql://" + palvelin + "/" + userstietokanta;
        }
        try {
            Class.forName(ajuri).newInstance();
        } catch (Exception e) {
            System.out.println("Virhe ajurin käyttöönotossa" + e.getMessage());
        }
        if (koti) {
            return DriverManager.getConnection(osoite, kayttajatunnus, salasana);
        } else {
            return DriverManager.getConnection(osoite, userskayttajatunnus, userssalasana);

        }
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

    public List<Tyotehtava> haeAsiakkaanTyotehtavat(int asiakas) throws SQLException, UnsupportedEncodingException {

        List<Tyotehtava> tyotehtavat = new ArrayList();
        Connection yhteys = luoYhteys();

        System.out.println("yhteys luotu");
        PreparedStatement valmisteltuKysely = yhteys.prepareStatement("select * from asiakas.tyotehtavat where asiakasnumero = ? order by toivepvm");
        //PreparedStatement valmisteltuKysely = yhteys.prepareStatement("select * from asiakas.tyotehtavat where asiakasnumero = ? AND tila = ? order by toivepvm");
        valmisteltuKysely.setInt(1, asiakas);
        //valmisteltuKysely.setString(2, String.valueOf('N'));
        //valmisteltuKysely.setString(3, String.valueOf('K'));

        ResultSet tulosjoukko = valmisteltuKysely.executeQuery();
//        Statement kysely = yhteys.createStatement();
//        ResultSet tulosjoukko = kysely.executeQuery("select * from asiakas.tyotehtavat");
//        
        while (tulosjoukko.next()) {
            int tyonumero = tulosjoukko.getInt("tyonumero");
            System.out.println("työnumero: " + tyonumero);
            int asiakasnumero = tulosjoukko.getInt("asiakasnumero");
            String tyolaji = tulosjoukko.getString("tyolaji");
            String tila = tulosjoukko.getString("tila");
            String kuvaus = tulosjoukko.getString("kuvaus");
            if (kuvaus != null) {
                kuvaus = URLDecoder.decode(kuvaus, koodaus);
            }
            String kadunnimi = tulosjoukko.getString("kadunnimi");
            if (kadunnimi != null) {
                kadunnimi = URLDecoder.decode(kadunnimi, koodaus);
            }
            String talonnumero = tulosjoukko.getString("talonnumero");
            if (talonnumero != null) {
                talonnumero = URLDecoder.decode(talonnumero, koodaus);
            }
            String postinumero = tulosjoukko.getString("postinumero");
            if (postinumero != null) {
                postinumero = URLDecoder.decode(postinumero, koodaus);
            }
            String postitoimipaikka = tulosjoukko.getString("postitoimipaikka");
            if (postitoimipaikka != null) {
                postitoimipaikka = URLDecoder.decode(postitoimipaikka, koodaus);
            }
            String asiakkaanyhteyshenkilo = tulosjoukko.getString("asiakkaanyhteyshenkilo");
            if (asiakkaanyhteyshenkilo != null) {
                asiakkaanyhteyshenkilo = URLDecoder.decode(asiakkaanyhteyshenkilo, koodaus);
            }

            String puhelinnumero = tulosjoukko.getString("puhelinnumero");
            if (puhelinnumero != null) {
                puhelinnumero = URLDecoder.decode(puhelinnumero, koodaus);
            }
            String vastuuhenkilo = tulosjoukko.getString("vastuuhenkilo");
            if (vastuuhenkilo != null) {
                vastuuhenkilo = URLDecoder.decode(vastuuhenkilo, koodaus);
            }
            Date pvm = tulosjoukko.getDate("toivepvm");

            String toivepvm = "";
            if (pvm != null) {
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                toivepvm = formatter.format(pvm);

            }


            Tyotehtava t = new Tyotehtava(tyonumero, asiakasnumero, tyolaji, tila,
                    kuvaus, kadunnimi, talonnumero, postinumero, postitoimipaikka,
                    asiakkaanyhteyshenkilo, puhelinnumero, vastuuhenkilo, toivepvm); // lisää parametrit
            tyotehtavat.add(t);
        }

        yhteys.close();
        return tyotehtavat;
    }

    public Tyotehtava haeTyotehtava(int tyonro) throws SQLException, UnsupportedEncodingException {
        Connection yhteys = luoYhteys();
        Tyotehtava t = null;
        System.out.println("yhteys luotu");
        PreparedStatement valmisteltuKysely = yhteys.prepareStatement("select * from asiakas.tyotehtavat where tyonumero = ?");
        valmisteltuKysely.setInt(1, tyonro);
        ResultSet tulosjoukko = valmisteltuKysely.executeQuery();

        while (tulosjoukko.next()) {
            int tyonumero = tulosjoukko.getInt("tyonumero");
            System.out.println("työnumero: " + tyonumero);
            int asiakasnumero = tulosjoukko.getInt("asiakasnumero");
            String tyolaji = tulosjoukko.getString("tyolaji");
            String tila = tulosjoukko.getString("tila");
            String kuvaus = tulosjoukko.getString("kuvaus");
            if (kuvaus != null) {
                kuvaus = URLDecoder.decode(kuvaus, koodaus);
            }
            String kadunnimi = tulosjoukko.getString("kadunnimi");
            if (kadunnimi != null) {
                kadunnimi = URLDecoder.decode(kadunnimi, koodaus);
            }
            String talonnumero = tulosjoukko.getString("talonnumero");
            if (talonnumero != null) {
                talonnumero = URLDecoder.decode(talonnumero, koodaus);
            }
            String postinumero = tulosjoukko.getString("postinumero");
            if (postinumero != null) {
                postinumero = URLDecoder.decode(postinumero, koodaus);
            }
            String postitoimipaikka = tulosjoukko.getString("postitoimipaikka");
            if (postitoimipaikka != null) {
                postitoimipaikka = URLDecoder.decode(postitoimipaikka, koodaus);
            }
            String asiakkaanyhteyshenkilo = tulosjoukko.getString("asiakkaanyhteyshenkilo");
            if (asiakkaanyhteyshenkilo != null) {
                asiakkaanyhteyshenkilo = URLDecoder.decode(asiakkaanyhteyshenkilo, koodaus);
            }

            String puhelinnumero = tulosjoukko.getString("puhelinnumero");
            if (puhelinnumero != null) {
                puhelinnumero = URLDecoder.decode(puhelinnumero, koodaus);
            }
            String vastuuhenkilo = tulosjoukko.getString("vastuuhenkilo");
            if (vastuuhenkilo != null) {
                vastuuhenkilo = URLDecoder.decode(vastuuhenkilo, koodaus);
            }
            Date pvm = tulosjoukko.getDate("toivepvm");

            String toivepvm = "";
            if (pvm != null) {
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                toivepvm = formatter.format(pvm);

            }


            t = new Tyotehtava(tyonumero, asiakasnumero, tyolaji, tila,
                    kuvaus, kadunnimi, talonnumero, postinumero, postitoimipaikka,
                    asiakkaanyhteyshenkilo, puhelinnumero, vastuuhenkilo, toivepvm); // lisää parametrit


        }
        yhteys.close();
        return t;
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
            String tila = tulosjoukko.getString("tila");
            String kuvaus = tulosjoukko.getString("kuvaus");
            kuvaus = URLDecoder.decode(kuvaus, koodaus);
            String kadunnimi = tulosjoukko.getString("kadunnimi");
            kadunnimi = URLDecoder.decode(kadunnimi, koodaus);
            String talonnumero = tulosjoukko.getString("talonnumero");
            talonnumero = URLDecoder.decode(talonnumero, koodaus);
            String postinumero = tulosjoukko.getString("postinumero");
            postinumero = URLDecoder.decode(postinumero, koodaus);
            String postitoimipaikka = tulosjoukko.getString("postitoimipaikka");
            postitoimipaikka = URLDecoder.decode(postitoimipaikka, koodaus);
            String asiakkaanyhteyshenkilo = tulosjoukko.getString("asiakkaanyhteyshenkilo");
            asiakkaanyhteyshenkilo = URLDecoder.decode(asiakkaanyhteyshenkilo, koodaus);
            String puhelinnumero = tulosjoukko.getString("puhelinnumero");
            puhelinnumero = URLDecoder.decode(puhelinnumero, koodaus);
            String vastuuhenkilo = tulosjoukko.getString("vastuuhenkilo");
            vastuuhenkilo = URLDecoder.decode(vastuuhenkilo, koodaus);
            //Date toivepvm = tulosjoukko.getDate("toivepvm");
            Date pvm = tulosjoukko.getDate("toivepvm");
            System.out.println("pvm: " + pvm);
            //String toivepvm = "12/06/2012";
            String toivepvm = "";
            if (pvm != null) {
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                toivepvm = formatter.format(pvm);
//            Date toivepvm = tulosjoukko.getDate("toivepvm");
            }


            Tyotehtava t = new Tyotehtava(tyonumero, asiakasnumero, tyolaji, tila,
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

//        String aputyolaji = tyotehtava.getTyolaji();
//        System.out.println("aputyolaji: " + aputyolaji);

        kysely.executeUpdate("insert into asiakas.tyotehtavat "
                + "(asiakasnumero, kuvaus, kadunnimi, talonnumero, "
                + "postinumero, postitoimipaikka, asiakkaanyhteyshenkilo, "
                + "puhelinnumero, vastuuhenkilo,tila, tyolaji, toivepvm) "
                + "values (" + tyotehtava.getAsiakasnumero() + ", '" + tyotehtava.getKuvaus() + "' , "
                + " '" + tyotehtava.getKadunnimi() + "', '" + tyotehtava.getTalonnumero() + "', "
                + " '" + tyotehtava.getPostinumero() + "', '" + tyotehtava.getPostitoimipaikka() + "', "
                + " '" + tyotehtava.getAsiakkaanyhteyshenkilo() + "', '" + tyotehtava.getPuhelinnumero() + "', "
                + " '" + tyotehtava.getVastuuhenkilo() + "', "
                + " '" + tyotehtava.getTila().charAt(0) + "', "
                + " '" + tyotehtava.getTyolaji().substring(0, 3) + "', "
                + " '" + tyotehtava.getToivepvm() + "' )");


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

    public Kayttaja haeKayttajatiedot(String ktunnus) throws SQLException {
        Kayttaja kayttaja = new Kayttaja();

        Connection yhteys = luoYhteys();

        System.out.println("yhteys luotu");
        PreparedStatement valmisteltuKysely = yhteys.prepareStatement("select * from asiakas.kayttajat where kayttajatunnus = ?");
        valmisteltuKysely.setString(1, ktunnus);
        ResultSet tulosjoukko = valmisteltuKysely.executeQuery();

        while (tulosjoukko.next()) {

            kayttaja.setKtunnus(tulosjoukko.getString("kayttajatunnus"));
            kayttaja.setSalasana(tulosjoukko.getString("salasana"));
            System.out.println(kayttaja.getKtunnus());
            System.out.println(kayttaja.getSalasana());
        }

        yhteys.close();
        return kayttaja;
    }

    public String haeTyontekija(String ktunnus) throws SQLException {
        String vastuuhenkilo = "";
        Connection yhteys = luoYhteys();

        System.out.println("yhteys luotu");
        PreparedStatement valmisteltuKysely = yhteys.prepareStatement("select * from asiakas.tyontekijat where kayttajatunnus = ?");
        valmisteltuKysely.setString(1, ktunnus);
        ResultSet tulosjoukko = valmisteltuKysely.executeQuery();

        while (tulosjoukko.next()) {
            vastuuhenkilo = tulosjoukko.getString("nimi");

        }

        yhteys.close();
        return vastuuhenkilo;
    }
    
    public int haeHenkilonumeroNimella(String tekija) throws SQLException {
        int henkilonumero = 0;
        Connection yhteys = luoYhteys();

        System.out.println("yhteys luotu");
        PreparedStatement valmisteltuKysely = yhteys.prepareStatement("select henkilonumero from asiakas.tyontekijat where nimi = ?");
        valmisteltuKysely.setString(1, tekija);
        

        ResultSet tulosjoukko = valmisteltuKysely.executeQuery();

        while (tulosjoukko.next()) {
            henkilonumero = tulosjoukko.getInt("henkilonumero");
        
        }

        yhteys.close();
        return henkilonumero;
    }

    public String haeAsiakkaanNimi(int asnro) throws SQLException {
        String nimi = "";
        Connection yhteys = luoYhteys();

        System.out.println("yhteys luotu");
        PreparedStatement valmisteltuKysely = yhteys.prepareStatement("select nimi from asiakas.asiakkaat where asiakasnumero = ?");
        valmisteltuKysely.setInt(1, asnro);
        ResultSet tulosjoukko = valmisteltuKysely.executeQuery();

        while (tulosjoukko.next()) {
            nimi = tulosjoukko.getString("nimi");

        }

        yhteys.close();
        return nimi;

    }
    public List<Tyotunti> haeTyotehtavanTunnit(int asiakas, int tyotehtava) throws SQLException, UnsupportedEncodingException {
        System.out.println("haeTyotehtavanTunnit");
        List<Tyotunti> tehdytMinuutit = new ArrayList();
        Connection yhteys = luoYhteys();

        System.out.println("yhteys luotu");
        PreparedStatement valmisteltuKysely = yhteys.prepareStatement
                ("select * from asiakas.tyotunnit where asiakasnumero = ? "
                + "and tyonumero = ?");
        
        valmisteltuKysely.setInt(1, asiakas);
        valmisteltuKysely.setInt(2, tyotehtava);
        System.out.println("asiakas: "+asiakas);
        System.out.println("tyotehtava: "+tyotehtava);
        ResultSet tulosjoukko = valmisteltuKysely.executeQuery();
        int i=1;
        while (tulosjoukko.next()) {
            System.out.println("i: "+i);
            i++;
            int asiakasnumero = tulosjoukko.getInt("asiakasnumero");
            int tyonumero = tulosjoukko.getInt("tyonumero");
            int henkilonumero = tulosjoukko.getInt("henkilonumero");
            int minuutit = tulosjoukko.getInt("minuutit");
            int hh = minuutit / 60;
            int mm = minuutit % 60;

            Tyotunti tt = new Tyotunti(asiakasnumero, tyonumero, henkilonumero, minuutit, hh, mm); 
            tehdytMinuutit.add(tt);
        }

        yhteys.close();
        return tehdytMinuutit;
    }

    public void lisaaTyotunnit(Tyotunti tt) throws SQLException {
           System.out.println("lisätään työtunnit");
        Connection yhteys = luoYhteys();
        
        PreparedStatement valmisteltuKysely = yhteys.prepareStatement("update asiakas.tyotunnit SET minuutit =  minuutit + ? WHERE asiakasnumero = ? AND tyonumero = ? AND henkilonumero = ?");
        //valmisteltuKysely.setDouble(1, tt.getMinuutit()*1.0);
        valmisteltuKysely.setInt(1, tt.getMinuutit());
        valmisteltuKysely.setInt(2, tt.getAsiakasnumero());
        valmisteltuKysely.setInt(3, tt.getTyonumero());
        valmisteltuKysely.setInt(4, tt.getHenkilonumero());

        int resultUpdate = valmisteltuKysely.executeUpdate();
        System.out.println("update tietuieita: "+resultUpdate);
        // update palauttaa tiedon kuinka monta tietuetta päivitettiin
        if (resultUpdate > 0) {
            yhteys.close();
            return;
        }
                
        Statement kysely = yhteys.createStatement();
        int resultInsert = kysely.executeUpdate("insert into asiakas.tyotunnit "
                                    + "(asiakasnumero, tyonumero, henkilonumero, minuutit) "
                                    + "values (" + tt.getAsiakasnumero() + ", " + tt.getTyonumero() 
                                    +", " +tt.getHenkilonumero() + ", " + tt.getMinuutit()*1.0 +")");
        System.out.println("insert palautti: "+resultInsert );

        yhteys.close();

        
    }

    

}
