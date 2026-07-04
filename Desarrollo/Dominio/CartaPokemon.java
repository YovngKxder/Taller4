package Dominio;

import Visitor.IVisitorPoder;

/**
 * Representa una carta de tipo Pokémon, con atributos de daño y cantidad de energías.
 * 
 * @author Maximiliano
 */
public class CartaPokemon extends Carta {

    private int daño;
    private int cantEnergias;

    public CartaPokemon(String nombreCarta, int rareza, int daño, int cantEnergias) {
        super(nombreCarta, rareza, "Pokemon");
        this.daño = daño;
        this.cantEnergias = cantEnergias;
    }

    public int getDaño() {
        return daño;
    }

    public void setDaño(int daño) {
        this.daño = daño;
    }

    public int getCantEnergias() {
        return cantEnergias;
    }

    public void setCantEnergias(int cantEnergias) {
        this.cantEnergias = cantEnergias;
    }

    @Override
    public double aceptar(IVisitorPoder visitor) {
        return visitor.visitarPokemon(this);
    }
    @Override
    public String toLinea() {
        return String.join(";", nombreCarta, String.valueOf(rareza), tipo,
                String.valueOf(daño), String.valueOf(cantEnergias));
    }
}
