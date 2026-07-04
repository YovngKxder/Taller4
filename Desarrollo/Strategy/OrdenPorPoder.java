package Strategy;

import java.util.LinkedList;
import Dominio.Carta;
import Visitor.VisitorPoder;

/**
 * Estrategia de ordenamiento por poder de la carta, de forma descendente.
 * Utiliza el Visitor para calcular el poder de cada carta al momento de comparar.
 * 
 * @author Maximiliano
 */
public class OrdenPorPoder implements IEstrategiaOrden {

    private VisitorPoder visitorPoder = new VisitorPoder();

    @Override
    public void ordenar(LinkedList<Carta> cartas) {
        cartas.sort((c1, c2) -> Double.compare(
                c2.aceptar(visitorPoder),
                c1.aceptar(visitorPoder)
        ));
    }
}