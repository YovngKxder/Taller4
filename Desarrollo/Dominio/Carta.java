package Dominio;

import Visitor.IVisitorPoder;

/**
 * Clase abstracta que representa una carta genérica de Pokémon TCG.
 * 
 * @author Maximiliano
 */
public abstract class Carta {

    protected String nombreCarta;
    protected int rareza;
    protected String tipo;

    public Carta(String nombreCarta, int rareza, String tipo) {
        this.nombreCarta = nombreCarta;
        this.rareza = rareza;
        this.tipo = tipo;
    }

    public String getNombreCarta() { return nombreCarta; }
    public void setNombreCarta(String nombreCarta) { this.nombreCarta = nombreCarta; }
    public int getRareza() { return rareza; }
    public void setRareza(int rareza) { this.rareza = rareza; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getRutaImagen() { return nombreCarta; }

    public abstract double aceptar(IVisitorPoder visitor);

    /**
     * Convierte la carta a su representación de línea de texto, en el mismo
     * formato usado por Sobres.txt (NombreCarta;Rareza;Tipo;...atributos).
     * Cada subclase agrega sus atributos adicionales propios.
     * @return la línea de texto lista para escribir en el archivo
     */
    public abstract String toLinea();

    @Override
    public String toString() {
        return nombreCarta + " (Rareza: " + rareza + ", Tipo: " + tipo + ")";
    }
}
