package Dominio;

import Visitor.IVisitorPoder;

/**
 * Representa una carta de tipo Energy, con un atributo de elemento.
 * 
 * @author Maximiliano
 */
public class CartaEnergy extends Carta {

    private String elemento;

    public CartaEnergy(String nombreCarta, int rareza, String elemento) {
        super(nombreCarta, rareza, "Energy");
        this.elemento = elemento;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    @Override
    public double aceptar(IVisitorPoder visitor) {
        return visitor.visitarEnergy(this);
    }
}
