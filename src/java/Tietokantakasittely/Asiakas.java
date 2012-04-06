/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietokantakasittely;

import java.util.Date;

/**
 *
 * @author Kati
 */
public class Asiakas {

    int asiakasnumero;
    String nimi;
    String kadunnimi;
    String talonnumero;
    String postinumero;
    String postitoimipaikka;
    String puhelinnumero;
    String yhteyshenkilo;
    Date asiakkaaksitulopvm;
    String tila;

    public Asiakas(int asiakasnumero, String nimi, String kadunnimi, String talonnumero, String postinumero, String postitoimipaikka, String puhelinnumero, String yhteyshenkilo, Date asiakkaaksitulopvm, String tila) {
        this.asiakasnumero = asiakasnumero;
        this.nimi = nimi;
        this.kadunnimi = kadunnimi;
        this.talonnumero = talonnumero;
        this.postinumero = postinumero;
        this.postitoimipaikka = postitoimipaikka;
        this.puhelinnumero = puhelinnumero;
        this.yhteyshenkilo = yhteyshenkilo;
        this.asiakkaaksitulopvm = asiakkaaksitulopvm;
        this.tila = tila;
    }

    public int getAsiakasnumero() {
        return asiakasnumero;
    }

    public Date getAsiakkaaksitulopvm() {
        return asiakkaaksitulopvm;
    }

    public String getKadunnimi() {
        return kadunnimi;
    }

    public String getNimi() {
        return nimi;
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

    public String getTila() {
        return tila;
    }

    public String getYhteyshenkilo() {
        return yhteyshenkilo;
    }

    public void setAsiakasnumero(int asiakasnumero) {
        this.asiakasnumero = asiakasnumero;
    }

    public void setAsiakkaaksitulopvm(Date asiakkaaksitulopvm) {
        this.asiakkaaksitulopvm = asiakkaaksitulopvm;
    }

    public void setKadunnimi(String kadunnimi) {
        this.kadunnimi = kadunnimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
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

    public void setTila(String tila) {
        this.tila = tila;
    }

    public void setYhteyshenkilo(String yhteyshenkilo) {
        this.yhteyshenkilo = yhteyshenkilo;
    }
}
