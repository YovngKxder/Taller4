package Strategy;

import java.util.LinkedList;
import Dominio.Carta;

/**
 * Estrategia de ordenamiento por rareza de forma descendente,
 * ya que mientras mayor la rareza, mejor es la carta.
 * 
 * @author Maximiliano
 */
public class OrdenPorRareza implements IEstrategiaOrden {

    @Override
    public void ordenar(LinkedList<Carta> cartas) {
        cartas.sort((c1, c2) -> c2.getRareza() - c1.getRareza());
    }
}