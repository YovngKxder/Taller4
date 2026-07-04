package Strategy;

import java.util.LinkedList;
import Dominio.Carta;

/**
 * Estrategia de ordenamiento alfabético por nombre de la carta.
 * 
 * @author Maximiliano
 */
public class OrdenPorNombre implements IEstrategiaOrden {

    @Override
    public void ordenar(LinkedList<Carta> cartas) {
        cartas.sort((c1, c2) -> c1.getNombreCarta().compareToIgnoreCase(c2.getNombreCarta()));
    }
}