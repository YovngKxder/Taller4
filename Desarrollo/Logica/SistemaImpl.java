package Logica;

import java.io.FileWriter;
import java.util.LinkedList;
import java.io.IOException;
import Dominio.*;
import Strategy.IEstrategiaOrden;

/**
 * Implementación del sistema de administración de cartas.
 * Aplica el patrón Singleton para garantizar una única instancia
 * de la colección durante toda la ejecución del programa.
 * Persiste automáticamente los cambios en Sobres.txt.
 * 
 * @author Maximiliano
 */
public class SistemaImpl implements ISistema {

    private static final String ruta = "Sobres.txt";

    private static SistemaImpl instancia;

    private LinkedList<Carta> listaCartas;
    private IEstrategiaOrden estrategiaOrden;

    private SistemaImpl() {
        listaCartas = new LinkedList<>();
    }

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
        guardarArchivo();
    }

    @Override
    public boolean eliminarCarta(Carta carta) {
        boolean eliminada = listaCartas.remove(carta);
        if (eliminada) {
            guardarArchivo();
        }
        return eliminada;
    }

    @Override
    public void modificarCartaPokemon(Carta carta, int daño, int cantEnergias) {
        if (carta instanceof CartaPokemon) {
            CartaPokemon pokemon = (CartaPokemon) carta;
            pokemon.setDaño(daño);
            pokemon.setCantEnergias(cantEnergias);
            guardarArchivo();
        }
    }

    @Override
    public void modificarCartaItem(Carta carta, int bonificacion) {
        if (carta instanceof CartaItem) {
            CartaItem item = (CartaItem) carta;
            item.setBonificacion(bonificacion);
            guardarArchivo();
        }
    }

    @Override
    public void modificarCartaSupporter(Carta carta, int efectosPorTurno) {
        if (carta instanceof CartaSupporter) {
            CartaSupporter supporter = (CartaSupporter) carta;
            supporter.setEfectosPorTurno(efectosPorTurno);
            guardarArchivo();
        }
    }

    @Override
    public void modificarCartaEnergy(Carta carta, String elemento) {
        if (carta instanceof CartaEnergy) {
            CartaEnergy energy = (CartaEnergy) carta;
            energy.setElemento(elemento);
            guardarArchivo();
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

    /**
     * Sobrescribe Sobres.txt con el estado actual de la colección,
     * manteniendo el formato original NombreCarta;Rareza;Tipo;...
     * Se llama automáticamente tras cada operación CRUD.
     */
    private void guardarArchivo() {
        try (FileWriter writer = new FileWriter(ruta)) {
            for (Carta carta : listaCartas) {
                writer.write(carta.toLinea() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
}
