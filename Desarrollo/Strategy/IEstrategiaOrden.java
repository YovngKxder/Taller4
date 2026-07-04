package Strategy;

import java.util.LinkedList;
import Dominio.Carta;

/**
 * Define el contrato para las distintas estrategias de ordenamiento
 * de la colección de cartas.
 * 
 * @author Maximiliano
 */
public interface IEstrategiaOrden {

    /**
     * Ordena la lista de cartas según el criterio definido por la estrategia concreta.
     * @param cartas la lista de cartas a ordenar (se modifica en el mismo lugar)
     */
    void ordenar(LinkedList<Carta> cartas);
}