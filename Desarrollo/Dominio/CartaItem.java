package Dominio;

import Visitor.IVisitorPoder;

/**
 * Representa una carta de tipo Item, con un atributo de bonificación.
 * 
 * @author Maximiliano
 */
public class CartaItem extends Carta {

    private int bonificacion;

    public CartaItem(String nombreCarta, int rareza, int bonificacion) {
        super(nombreCarta, rareza, "Item");
        this.bonificacion = bonificacion;
    }

    public int getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(int bonificacion) {
        this.bonificacion = bonificacion;
    }

    @Override
    public double aceptar(IVisitorPoder visitor) {
        return visitor.visitarItem(this);
    }
}
