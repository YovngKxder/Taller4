package Visitor;
import Dominio.*;
/**
 * Implementación concreta del Visitor que calcula el poder de cada carta
 * según las reglas definidas para cada tipo.
 * 
 * @author Maiximiliano
 */
public class VisitorPoder implements IVisitorPoder {

    /**
     * Calcula el poder de una carta Pokémon como (daño/cantEnergias)*100.
     * @param carta la carta Pokémon a evaluar
     * @return el poder calculado
     */
    @Override
    public double visitarPokemon(CartaPokemon carta) {
        if (carta.getCantEnergias() == 0) {
            return 0;
        }
        return ((double) carta.getDaño() / carta.getCantEnergias()) * 100;
    }

    /**
     * Calcula el poder de una carta Item como bonificacion*20.
     * @param carta la carta Item a evaluar
     * @return el poder calculado
     */
    @Override
    public double visitarItem(CartaItem carta) {
        return carta.getBonificacion() * 20;
    }

    /**
     * Calcula el poder de una carta Supporter como efectosPorTurno*50.
     * @param carta la carta Supporter a evaluar
     * @return el poder calculado
     */
    @Override
    public double visitarSupporter(CartaSupporter carta) {
        return carta.getEfectosPorTurno() * 50;
    }

    /**
     * Calcula el poder de una carta Energy, siempre igual a 1 por defecto.
     * @param carta la carta Energy a evaluar
     * @return el poder por defecto (1)
     */
    @Override
    public double visitarEnergy(CartaEnergy carta) {
        return 1;
    }
}
