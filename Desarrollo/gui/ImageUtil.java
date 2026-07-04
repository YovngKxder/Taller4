package gui;

import javax.swing.ImageIcon;
import java.io.File;

/**
 * Utilidad para buscar la imagen correspondiente a una carta según su nombre.
 * Si no existe ninguna imagen con extensión png/jpg/jpeg, retorna una imagen
 * por defecto.
 * 
 * @author Maximiliano
 */
public class ImageUtil {

    private static final String CARPETA_IMAGENES = "imagenes/";
    private static final String IMAGEN_DEFECTO = CARPETA_IMAGENES + "default.png";
    private static final String[] EXTENSIONES = {"png", "jpg", "jpeg"};

    /**
     * Busca la imagen de una carta según su nombre, probando distintas extensiones.
     * @param nombreCarta el nombre de la carta
     * @return un ImageIcon con la imagen encontrada, o la imagen por defecto si no existe
     */
    public static ImageIcon buscarImagen(String nombreCarta) {
        for (String ext : EXTENSIONES) {
            File archivo = new File(CARPETA_IMAGENES + nombreCarta + "." + ext);
            if (archivo.exists()) {
                return new ImageIcon(archivo.getPath());
            }
        }
        return new ImageIcon(IMAGEN_DEFECTO);
    }

    /**
     * Escala un ImageIcon a un ancho y alto determinados.
     * @param icon el ícono original
     * @param ancho ancho deseado
     * @param alto alto deseado
     * @return el ícono escalado
     */
    public static ImageIcon escalar(ImageIcon icon, int ancho, int alto) {
        return new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_SMOOTH));
    }
}