//Maximiliano Edhin Abd-El-Kader Gongora, 22.128.215-9, Ingeniería Civil en Computación e Informática (ICCI)
package Logica;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import gui.VentanaAdministracion;

/**
 * Clase principal del sistema. Se encarga de leer el archivo Sobres.txt,
 * cargar las cartas en el sistema mediante el Singleton SistemaImpl,
 * y luego mostrar la interfaz gráfica de administración.
 * 
 * @author Maximiliano
 */
public class Main {

    public static void main(String[] args) {
        ISistema sistema = SistemaImpl.getInstance();

        try {
            Scanner sc = new Scanner(new File("Sobres.txt"));

            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea != null) {
                    sistema.agregarCarta(linea);
                }
            }
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo Sobres.txt");
            return;
        }

        VentanaAdministracion ventana = new VentanaAdministracion();
        ventana.setVisible(true);
    }
}
