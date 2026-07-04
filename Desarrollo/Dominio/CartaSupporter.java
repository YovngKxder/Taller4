package Dominio;

import Visitor.IVisitorPoder;

/**
 * Representa una carta de tipo Supporter, con un atributo de efectos por turno.
 * 
 * @author Maximiliano
 */
public class CartaSupporter extends Carta {

    private int efectosPorTurno;

    public CartaSupporter(String nombreCarta, int rareza, int efectosPorTurno) {
        super(nombreCarta, rareza, "Supporter");
        this.efectosPorTurno = efectosPorTurno;
    }

    public int getEfectosPorTurno() {
        return efectosPorTurno;
    }

    public void setEfectosPorTurno(int efectosPorTurno) {
        this.efectosPorTurno = efectosPorTurno;
    }

    @Override
    public double aceptar(IVisitorPoder visitor) {
        return visitor.visitarSupporter(this);
    }
}
