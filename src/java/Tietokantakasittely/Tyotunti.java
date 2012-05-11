/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietokantakasittely;

/**
 *
 * @author Kati
 */
public class Tyotunti {

    private int asiakasnumero;
    private int tyonumero;
    private int henkilonumero;
    private int minuutit;
    private int hh;
    private int mm;
    private int henkilonimi;

    public Tyotunti() {
    }

    
    public Tyotunti(int asiakasnumero, int tyonumero, int henkilonumero, int minuutit) {
        this.asiakasnumero = asiakasnumero;
        this.tyonumero = tyonumero;
        this.henkilonumero = henkilonumero;
        this.minuutit = minuutit;
    }

    public Tyotunti(int asiakasnumero, int tyonumero, int henkilonumero, int minuutit, int hh, int mm) {
        this.asiakasnumero = asiakasnumero;
        this.tyonumero = tyonumero;
        this.henkilonumero = henkilonumero;
        this.minuutit = minuutit;
        this.hh = hh;
        this.mm = mm;
    }
    
    

    public int getHenkilonimi() {
        return henkilonimi;
    }

    public int getHh() {
        return hh;
    }

    public int getMm() {
        return mm;
    }


    public int getAsiakasnumero() {
        return asiakasnumero;
    }

    public int getHenkilonumero() {
        return henkilonumero;
    }

    public int getMinuutit() {
        return minuutit;
    }

    public int getTyonumero() {
        return tyonumero;
    }

    public void setHenkilonimi(int henkilonimi) {
        this.henkilonimi = henkilonimi;
    }

    public void setHh(int hh) {
        this.hh = hh;
    }

    public void setMm(int mm) {
        this.mm = mm;
    }

    public void setAsiakasnumero(int asiakasnumero) {
        this.asiakasnumero = asiakasnumero;
    }

    public void setHenkilonumero(int henkilonumero) {
        this.henkilonumero = henkilonumero;
    }

    public void setMinuutit(int minuutit) {
        this.minuutit = minuutit;
    }

    public void setTyonumero(int tyonumero) {
        this.tyonumero = tyonumero;
    }
    
    
}
