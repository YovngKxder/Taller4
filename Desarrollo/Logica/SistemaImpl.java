package Logica;

import java.util.LinkedList;
import Dominio.*;
import Strategy.IEstrategiaOrden;

/**
 * Implementación del sistema de administración de cartas.
 * Aplica el patrón Singleton para garantizar una única instancia
 * de la colección durante toda la ejecución del programa.
 * 
 * @author Maximiliano
 */
public class SistemaImpl implements ISistema {

    private static SistemaImpl instancia;

    private LinkedList<Carta> listaCartas;
    private IEstrategiaOrden estrategiaOrden;

    /**
     * Constructor privado, evita instanciación externa (Singleton).
     */
    private SistemaImpl() {
        listaCartas = new LinkedList<>();
    }

    /**
     * Retorna la única instancia del sistema, creándola si aún no existe.
     * @return la instancia única de SistemaImpl
     */
    public static SistemaImpl getInstance() {
        if (instancia == null) {
            instancia = new SistemaImpl();
        }
        return instancia;
    }

    @Override
    public void agregarCarta(String linea) {
        Carta carta = CartaFactory.crearCartaDesdeLinea(linea);
        listaCartas.add(carta);
    }

    @Override
    public boolean eliminarCarta(Carta carta) {
        return listaCartas.remove(carta);
    }

    @Override
    public void modificarCartaPokemon(Carta carta, int daño, int cantEnergias) {
        if (carta instanceof CartaPokemon) {
            CartaPokemon pokemon = (CartaPokemon) carta;
            pokemon.setDaño(daño);
            pokemon.setCantEnergias(cantEnergias);
        }
    }

    @Override
    public void modificarCartaItem(Carta carta, int bonificacion) {
        if (carta instanceof CartaItem) {
            CartaItem item = (CartaItem) carta;
            item.setBonificacion(bonificacion);
        }
    }

    @Override
    public void modificarCartaSupporter(Carta carta, int efectosPorTurno) {
        if (carta instanceof CartaSupporter) {
            CartaSupporter supporter = (CartaSupporter) carta;
            supporter.setEfectosPorTurno(efectosPorTurno);
        }
    }

    @Override
    public void modificarCartaEnergy(Carta carta, String elemento) {
        if (carta instanceof CartaEnergy) {
            CartaEnergy energy = (CartaEnergy) carta;
            energy.setElemento(elemento);
        }
    }

    @Override
    public LinkedList<Carta> listarCartas() {
        if (estrategiaOrden != null) {
            estrategiaOrden.ordenar(listaCartas);
        }
        return listaCartas;
    }

    @Override
    public void setEstrategiaOrden(IEstrategiaOrden estrategia) {
        this.estrategiaOrden = estrategia;
    }
}