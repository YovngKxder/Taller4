package Logica;


import Dominio.*;
/**
 * Fábrica encargada de crear instancias de Carta según el tipo indicado
 * en cada línea del archivo Sobres.txt. Centraliza la lógica de creación,
 * evitando condicionales dispersos en el resto del sistema.
 * 
 * @author Maximiliano
 */
public class CartaFactory {

    /**
     * Crea una carta a partir de los datos ya separados de una línea del .txt.
     * El formato esperado es: NombreCarta;Rareza;Tipo;...atributosAdicionales
     * 
     * @param datos arreglo con los campos de la línea, ya divididos por ";"
     * @return la instancia de Carta correspondiente según el tipo
     * @throws IllegalArgumentException si el tipo de carta no es reconocido
     */
    public static Carta crearCarta(String[] datos) {
        String nombreCarta = datos[0];
        int rareza = Integer.parseInt(datos[1]);
        String tipo = datos[2];

        switch (tipo) {
            case "Pokemon":
                int daño = Integer.parseInt(datos[3]);
                int cantEnergias = Integer.parseInt(datos[4]);
                return new CartaPokemon(nombreCarta, rareza, daño, cantEnergias);

            case "Item":
                int bonificacion = Integer.parseInt(datos[3]);
                return new CartaItem(nombreCarta, rareza, bonificacion);

            case "Supporter":
                int efectosPorTurno = Integer.parseInt(datos[3]);
                return new CartaSupporter(nombreCarta, rareza, efectosPorTurno);

            case "Energy":
                String elemento = datos[3];
                return new CartaEnergy(nombreCarta, rareza, elemento);

            default:
                throw new IllegalArgumentException("Tipo de carta no reconocido: " + tipo);
        }
    }

    /**
     * Crea una carta directamente a partir de una línea cruda del archivo .txt.
     * @param linea línea completa en formato NombreCarta;Rareza;Tipo;...
     * @return la instancia de Carta correspondiente
     */
    public static Carta crearCartaDesdeLinea(String linea) {
        String[] datos = linea.split(";");
        return crearCarta(datos);
    }
}