package Visitor;
import Dominio.*;

/**
 * Interfaz del patrón Visitor. Define las operaciones de cálculo de poder
 * para cada tipo concreto de carta, permitiendo agregar nuevas operaciones
 * sin modificar las clases de Dominio.
 * 
 * @author Maximiliano
 */
public interface IVisitorPoder {

    /**
     * Calcula el poder de una carta de tipo Pokémon.
     * @param carta la carta Pokémon a evaluar
     * @return el poder calculado
     */
    double visitarPokemon(CartaPokemon carta);

    /**
     * Calcula el poder de una carta de tipo Item.
     * @param carta la carta Item a evaluar
     * @return el poder calculado
     */
    double visitarItem(CartaItem carta);

    /**
     * Calcula el poder de una carta de tipo Supporter.
     * @param carta la carta Supporter a evaluar
     * @return el poder calculado
     */
    double visitarSupporter(CartaSupporter carta);

    /**
     * Calcula el poder de una carta de tipo Energy.
     * @param carta la carta Energy a evaluar
     * @return el poder calculado
     */
    double visitarEnergy(CartaEnergy carta);
}
