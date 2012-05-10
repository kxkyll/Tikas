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

    public Tyotunti() {
    }

    
    public Tyotunti(int asiakasnumero, int tyonumero, int henkilonumero, int minuutit) {
        this.asiakasnumero = asiakasnumero;
        this.tyonumero = tyonumero;
        this.henkilonumero = henkilonumero;
        this.minuutit = minuutit;
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
