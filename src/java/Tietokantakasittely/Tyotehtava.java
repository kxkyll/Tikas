/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietokantakasittely;

/**
 *
 * @author Kati
 */
/*
 * tyonumero serial PRIMARY KEY, asiakasnumero int, tyolaji char (3), --
 * KON,YLL,SUU,TOT tila char (1), -- N, K, L, P, V kuvaus varchar (100),
 * kadunnimi varchar (50), talonnumero varchar (10), postinumero varchar (10),
 * postitoimipaikka varchar (15), asiakkaanyhteyshenkilo varchar (25),
 * puhelinnumero varchar (15), vastuuhenkilo varchar (25), toivepvm date,
 * CONSTRAINT fk_asiakas FOREIGN KEY (asiakasnumero) REFERENCES
 * asiakas.asiakkaat (asiakasnumero)
 *
 */
import java.sql.Date;

public class Tyotehtava {

    private int tyonumero;
    private int asiakasnumero;
    private String tyolaji;
    private String tila;
    private String kuvaus;
    private String kadunnimi;
    private String talonnumero;
    private String postinumero;
    private String postitoimipaikka;
    private String asiakkaanyhteyshenkilo;
    private String puhelinnumero;
    private String vastuuhenkilo;
    private Date toivepvm;

    public Tyotehtava() {
    }

    public Tyotehtava(int tyonumero, int asiakasnumero, String tyolaji, String tila, String kuvaus, String kadunnimi, String talonnumero, String postinumero, String postitoimipaikka, String asiakkaanyhteyshenkilo, String puhelinnumero, String vastuuhenkilo, Date toivepvm) {
        this.tyonumero = tyonumero;
        this.asiakasnumero = asiakasnumero;
        this.tyolaji = tyolaji;
        this.tila = tila;
        this.kuvaus = kuvaus;
        this.kadunnimi = kadunnimi;
        this.talonnumero = talonnumero;
        this.postinumero = postinumero;
        this.postitoimipaikka = postitoimipaikka;
        this.asiakkaanyhteyshenkilo = asiakkaanyhteyshenkilo;
        this.puhelinnumero = puhelinnumero;
        this.vastuuhenkilo = vastuuhenkilo;
        this.toivepvm = toivepvm;
    }

    public int getAsiakasnumero() {
        return asiakasnumero;
    }

    public String getAsiakkaanyhteyshenkilo() {
        return asiakkaanyhteyshenkilo;
    }

    public String getKadunnimi() {
        return kadunnimi;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public String getPostinumero() {
        return postinumero;
    }

    public String getPostitoimipaikka() {
        return postitoimipaikka;
    }

    public String getPuhelinnumero() {
        return puhelinnumero;
    }

    public String getTalonnumero() {
        return talonnumero;
    }

    public String getTyolaji() {
        return tyolaji;
    }
    
    public String getTila() {
        return tila;
    }

    public Date getToivepvm() {
        return toivepvm;
    }

    public int getTyonumero() {
        return tyonumero;
    }

    public String getVastuuhenkilo() {
        return vastuuhenkilo;
    }

    public void setAsiakasnumero(int asiakasnumero) {
        this.asiakasnumero = asiakasnumero;
    }

    public void setAsiakkaanyhteyshenkilo(String asiakkaanyhteyshenkilo) {
        this.asiakkaanyhteyshenkilo = asiakkaanyhteyshenkilo;
    }

    public void setKadunnimi(String kadunnimi) {
        this.kadunnimi = kadunnimi;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public void setPostinumero(String postinumero) {
        this.postinumero = postinumero;
    }

    public void setPostitoimipaikka(String postitoimipaikka) {
        this.postitoimipaikka = postitoimipaikka;
    }

    public void setPuhelinnumero(String puhelinnumero) {
        this.puhelinnumero = puhelinnumero;
    }

    public void setTalonnumero(String talonnumero) {
        this.talonnumero = talonnumero;
    }

    public void setTyolaji(String tyolaji) {
        this.tyolaji = tyolaji;
    }
    public void setTila(String tila) {
        this.tila = tila;
    }

    public void setToivepvm(Date toivepvm) {
        this.toivepvm = toivepvm;
    }

    public void setTyonumero(int tyonumero) {
        this.tyonumero = tyonumero;
    }

    public void setVastuuhenkilo(String vastuuhenkilo) {
        this.vastuuhenkilo = vastuuhenkilo;
    }

    @Override
    public String toString() {
        return "Tyotehtava{" + "tyonumero=" + tyonumero + ", asiakasnumero=" + asiakasnumero + ", tila=" + tila + ", kuvaus=" + kuvaus + ", kadunnimi=" + kadunnimi + ", talonnumero=" + talonnumero + ", postinumero=" + postinumero + ", postitoimipaikka=" + postitoimipaikka + ", asiakkaanyhteyshenkilo=" + asiakkaanyhteyshenkilo + ", puhelinnumero=" + puhelinnumero + ", vastuuhenkilo=" + vastuuhenkilo + ", toivepvm=" + toivepvm + '}';
    }

}
