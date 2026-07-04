package Logica;

import java.util.LinkedList;
import Dominio.Carta;
import Strategy.IEstrategiaOrden;

/**
 * Define las operaciones principales del sistema de administración
 * de cartas de la colección Pokémon TCG.
 * 
 * @author Maximiliano
 */
public interface ISistema {

    /**
     * Crea una carta a partir de una línea del archivo Sobres.txt y la agrega
     * a la colección.
     * @param linea línea cruda en formato NombreCarta;Rareza;Tipo;...
     */
    void agregarCarta(String linea);

    /**
     * Elimina una carta de la colección.
     * @param carta la carta a eliminar
     * @return true si se eliminó correctamente, false si no se encontró
     */
    boolean eliminarCarta(Carta carta);

    /**
     * Modifica los atributos adicionales de una carta de tipo Pokémon.
     * @param carta la carta a modificar
     * @param daño nuevo valor de daño
     * @param cantEnergias nueva cantidad de energías
     */
    void modificarCartaPokemon(Carta carta, int daño, int cantEnergias);

    /**
     * Modifica el atributo adicional de una carta de tipo Item.
     * @param carta la carta a modificar
     * @param bonificacion nuevo valor de bonificación
     */
    void modificarCartaItem(Carta carta, int bonificacion);

    /**
     * Modifica el atributo adicional de una carta de tipo Supporter.
     * @param carta la carta a modificar
     * @param efectosPorTurno nuevo valor de efectos por turno
     */
    void modificarCartaSupporter(Carta carta, int efectosPorTurno);

    /**
     * Modifica el atributo adicional de una carta de tipo Energy.
     * @param carta la carta a modificar
     * @param elemento nuevo elemento
     */
    void modificarCartaEnergy(Carta carta, String elemento);

    /**
     * Retorna la colección completa de cartas ordenada según la estrategia
     * de ordenamiento configurada actualmente.
     * @return lista de cartas ordenada
     */
    LinkedList<Carta> listarCartas();

    /**
     * Define la estrategia de ordenamiento a utilizar al listar las cartas.
     * @param estrategia la estrategia de ordenamiento (Rareza, Nombre o Poder)
     */
    void setEstrategiaOrden(IEstrategiaOrden estrategia);
}
