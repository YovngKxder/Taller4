package Dominio;

import Visitor.IVisitorPoder;

/**
 * Clase abstracta que representa una carta genérica de Pokémon TCG.
 * Contiene los atributos comunes a todas las cartas y define el método
 * abstracto para el patrón Visitor, usado para calcular el poder de cada carta.
 * 
 * @author Maximiliano
 */
public abstract class Carta {
    
    protected String nombreCarta;
    protected int rareza;
    protected String tipo;

    /**
     * Constructor de la clase Carta.
     * @param nombreCarta nombre de la carta
     * @param rareza nivel de rareza de la carta (mientras mayor, mejor)
     * @param tipo tipo de carta (Pokemon, Item, Supporter, Energy)
     */
    public Carta(String nombreCarta, int rareza, String tipo) {
        this.nombreCarta = nombreCarta;
        this.rareza = rareza;
        this.tipo = tipo;
    }

    public String getNombreCarta() {
        return nombreCarta;
    }

    public void setNombreCarta(String nombreCarta) {
        this.nombreCarta = nombreCarta;
    }

    public int getRareza() {
        return rareza;
    }

    public void setRareza(int rareza) {
        this.rareza = rareza;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Retorna la ruta esperada de la imagen de la carta según su nombre.
     * @return ruta relativa de la imagen (sin extensión definida, a criterio del sistema)
     */
    public String getRutaImagen() {
        return nombreCarta;
    }

    /**
     * Método abstracto del patrón Visitor. Cada subclase debe implementar
     * cómo se deja "visitar" para calcular su poder correspondiente.
     * @param visitor el visitante que calculará el poder
     * @return el poder calculado de la carta
     */
    public abstract double aceptar(IVisitorPoder visitor);

    @Override
    public String toString() {
        return nombreCarta + " (Rareza: " + rareza + ", Tipo: " + tipo + ")";
    }
}
