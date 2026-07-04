package gui;

import javax.swing.*;
import java.awt.*;
import Dominio.*;
import Visitor.VisitorPoder;

/**
 * Ventana de visualización ampliada de una carta, mostrando su imagen,
 * atributos según su tipo, y su poder calculado mediante el Visitor.
 * 
 * @author Maximiliano
 */
public class VentanaDetalleCarta extends JFrame {

    public VentanaDetalleCarta(Carta carta) {
        setTitle("Detalle: " + carta.getNombreCarta());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(350, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel imagen = new JLabel(ImageUtil.escalar(ImageUtil.buscarImagen(carta.getNombreCarta()), 200, 200));
        imagen.setHorizontalAlignment(SwingConstants.CENTER);
        add(imagen, BorderLayout.NORTH);

        JPanel panelDatos = new JPanel(new GridLayout(0, 1, 5, 5));
        panelDatos.add(new JLabel("Nombre: " + carta.getNombreCarta()));
        panelDatos.add(new JLabel("Rareza: " + carta.getRareza()));
        panelDatos.add(new JLabel("Tipo: " + carta.getTipo()));

        if (carta instanceof CartaPokemon) {
            CartaPokemon p = (CartaPokemon) carta;
            panelDatos.add(new JLabel("Daño: " + p.getDaño()));
            panelDatos.add(new JLabel("Cant. Energías: " + p.getCantEnergias()));
        } else if (carta instanceof CartaItem) {
            CartaItem i = (CartaItem) carta;
            panelDatos.add(new JLabel("Bonificación: " + i.getBonificacion()));
        } else if (carta instanceof CartaSupporter) {
            CartaSupporter s = (CartaSupporter) carta;
            panelDatos.add(new JLabel("Efectos Por Turno: " + s.getEfectosPorTurno()));
        } else if (carta instanceof CartaEnergy) {
            CartaEnergy en = (CartaEnergy) carta;
            panelDatos.add(new JLabel("Elemento: " + en.getElemento()));
        }

        double poder = carta.aceptar(new VisitorPoder());
        JLabel lblPoder = new JLabel(String.format("Poder: %.2f", poder));
        lblPoder.setFont(lblPoder.getFont().deriveFont(Font.BOLD, 16f));
        panelDatos.add(lblPoder);

        add(panelDatos, BorderLayout.CENTER);
    }
}