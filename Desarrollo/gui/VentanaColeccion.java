package gui;

import javax.swing.*;
import java.awt.*;
import Dominio.Carta;
import Logica.*;
import Strategy.*;

/**
 * Ventana que muestra la colección de cartas en formato de grilla tipo álbum,
 * permitiendo ordenarlas por Rareza, Nombre o Poder.
 * 
 * @author Maximiliano
 */
public class VentanaColeccion extends JFrame {

    private ISistema sistema;
    private JPanel panelGrilla;
    private JComboBox<String> comboOrden;

    public VentanaColeccion() {
        sistema = SistemaImpl.getInstance();
        setTitle("Colección de Cartas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 550);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        add(crearPanelSuperior(), BorderLayout.NORTH);

        panelGrilla = new JPanel(new GridLayout(0, 4, 10, 10));
        JScrollPane scroll = new JScrollPane(panelGrilla);
        add(scroll, BorderLayout.CENTER);

        refrescarGrilla();
    }

    private JPanel crearPanelSuperior() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Ordenar por:"));

        comboOrden = new JComboBox<>(new String[]{"Rareza", "Nombre", "Poder"});
        comboOrden.addActionListener(e -> {
            aplicarEstrategiaOrden();
            refrescarGrilla();
        });
        panel.add(comboOrden);

        return panel;
    }

    /**
     * Aplica la estrategia de ordenamiento seleccionada en el combo.
     */
    private void aplicarEstrategiaOrden() {
        String seleccion = (String) comboOrden.getSelectedItem();
        switch (seleccion) {
            case "Rareza":
                sistema.setEstrategiaOrden(new OrdenPorRareza());
                break;
            case "Nombre":
                sistema.setEstrategiaOrden(new OrdenPorNombre());
                break;
            case "Poder":
                sistema.setEstrategiaOrden(new OrdenPorPoder());
                break;
        }
    }

    /**
     * Reconstruye la grilla de botones con la imagen y nombre de cada carta,
     * usando el orden actual de la colección.
     */
    private void refrescarGrilla() {
        panelGrilla.removeAll();

        for (Carta carta : sistema.listarCartas()) {
            JButton boton = new JButton(carta.getNombreCarta());
            boton.setIcon(ImageUtil.escalar(ImageUtil.buscarImagen(carta.getNombreCarta()), 100, 100));
            boton.setVerticalTextPosition(SwingConstants.BOTTOM);
            boton.setHorizontalTextPosition(SwingConstants.CENTER);
            boton.addActionListener(e -> new VentanaDetalleCarta(carta).setVisible(true));

            panelGrilla.add(boton);
        }

        panelGrilla.revalidate();
        panelGrilla.repaint();
    }
}