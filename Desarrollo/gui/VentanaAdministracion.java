package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import Dominio.*;
import Logica.ISistema;
import Logica.SistemaImpl;


/**
 * Ventana de administración de cartas. Permite agregar, eliminar y modificar
 * cartas de la colección.
 * 
 * @author Maximiliano
 */
public class VentanaAdministracion extends JFrame {

    private ISistema sistema;

    private JComboBox<String> comboTipo;
    private JTextField txtNombre;
    private JTextField txtRareza;

    // Paneles dinámicos según tipo
    private CardLayout cardLayout;
    private JPanel panelAtributos;

    private JTextField txtDaño, txtCantEnergias;
    private JTextField txtBonificacion;
    private JTextField txtEfectosPorTurno;
    private JTextField txtElemento;

    private JComboBox<Carta> comboCartas;

    public VentanaAdministracion() {
        sistema = SistemaImpl.getInstance();
        setTitle("Administración de Cartas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 480);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        add(crearPanelFormulario(), BorderLayout.CENTER);
        add(crearPanelBotones(), BorderLayout.SOUTH);
    }

    /**
     * Crea el panel principal del formulario, incluyendo datos comunes
     * y los atributos dinámicos según el tipo de carta.
     * @return el panel armado
     */
    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        comboTipo = new JComboBox<>(new String[]{"Pokemon", "Item", "Supporter", "Energy"});
        comboTipo.addActionListener(this::onCambioTipo);

        txtNombre = new JTextField(15);
        txtRareza = new JTextField(15);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        panel.add(comboTipo, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        panel.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Rareza:"), gbc);
        gbc.gridx = 1;
        panel.add(txtRareza, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Carta existente:"), gbc);
        comboCartas = new JComboBox<>();
        actualizarComboCartas();
        gbc.gridx = 1;
        panel.add(comboCartas, gbc);

        cardLayout = new CardLayout();
        panelAtributos = new JPanel(cardLayout);
        panelAtributos.add(crearPanelPokemon(), "Pokemon");
        panelAtributos.add(crearPanelItem(), "Item");
        panelAtributos.add(crearPanelSupporter(), "Supporter");
        panelAtributos.add(crearPanelEnergy(), "Energy");

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panel.add(panelAtributos, gbc);

        return panel;
    }

    private JPanel crearPanelPokemon() {
        JPanel p = new JPanel(new GridLayout(2, 2, 5, 5));
        txtDaño = new JTextField();
        txtCantEnergias = new JTextField();
        p.add(new JLabel("Daño:"));
        p.add(txtDaño);
        p.add(new JLabel("Cant. Energías:"));
        p.add(txtCantEnergias);
        return p;
    }

    private JPanel crearPanelItem() {
        JPanel p = new JPanel(new GridLayout(1, 2, 5, 5));
        txtBonificacion = new JTextField();
        p.add(new JLabel("Bonificación:"));
        p.add(txtBonificacion);
        return p;
    }

    private JPanel crearPanelSupporter() {
        JPanel p = new JPanel(new GridLayout(1, 2, 5, 5));
        txtEfectosPorTurno = new JTextField();
        p.add(new JLabel("Efectos Por Turno:"));
        p.add(txtEfectosPorTurno);
        return p;
    }

    private JPanel crearPanelEnergy() {
        JPanel p = new JPanel(new GridLayout(1, 2, 5, 5));
        txtElemento = new JTextField();
        p.add(new JLabel("Elemento:"));
        p.add(txtElemento);
        return p;
    }

    /**
     * Cambia el panel de atributos visible según el tipo seleccionado.
     * @param e evento de selección del combo
     */
    private void onCambioTipo(ActionEvent e) {
        cardLayout.show(panelAtributos, (String) comboTipo.getSelectedItem());
    }

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel();

        JButton btnAgregar = new JButton("Agregar Carta");
        btnAgregar.addActionListener(this::agregarCarta);

        JButton btnEliminar = new JButton("Eliminar Carta");
        btnEliminar.addActionListener(this::eliminarCarta);

        JButton btnModificar = new JButton("Modificar Carta");
        btnModificar.addActionListener(this::modificarCarta);

        JButton btnVerColeccion = new JButton("Ver Colección");
        btnVerColeccion.addActionListener(e -> new VentanaColeccion().setVisible(true));

        panel.add(btnAgregar);
        panel.add(btnEliminar);
        panel.add(btnModificar);
        panel.add(btnVerColeccion);

        return panel;
    }

    /**
     * Agrega una carta nueva construyendo la línea en el mismo formato
     * del archivo Sobres.txt, y delegando la creación al sistema (Factory).
     * @param e evento del botón
     */
    private void agregarCarta(ActionEvent e) {
        try {
            String nombre = txtNombre.getText().trim();
            String rareza = txtRareza.getText().trim();
            String tipo = (String) comboTipo.getSelectedItem();

            String linea;
            switch (tipo) {
                case "Pokemon":
                    linea = String.join(";", nombre, rareza, tipo, txtDaño.getText().trim(), txtCantEnergias.getText().trim());
                    break;
                case "Item":
                    linea = String.join(";", nombre, rareza, tipo, txtBonificacion.getText().trim());
                    break;
                case "Supporter":
                    linea = String.join(";", nombre, rareza, tipo, txtEfectosPorTurno.getText().trim());
                    break;
                default:
                    linea = String.join(";", nombre, rareza, tipo, txtElemento.getText().trim());
            }

            sistema.agregarCarta(linea);
            actualizarComboCartas();
            JOptionPane.showMessageDialog(this, "Carta agregada correctamente.");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar la carta: revisa los datos ingresados.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Elimina la carta seleccionada en el combo de cartas existentes.
     * @param e evento del botón
     */
    private void eliminarCarta(ActionEvent e) {
        Carta seleccionada = (Carta) comboCartas.getSelectedItem();
        if (seleccionada == null) {
            JOptionPane.showMessageDialog(this, "No hay carta seleccionada.");
            return;
        }
        sistema.eliminarCarta(seleccionada);
        actualizarComboCartas();
        JOptionPane.showMessageDialog(this, "Carta eliminada correctamente.");
    }

    /**
     * Modifica los atributos adicionales de la carta seleccionada, según su tipo,
     * usando los valores actuales ingresados en el panel de atributos.
     * @param e evento del botón
     */
    private void modificarCarta(ActionEvent e) {
        Carta seleccionada = (Carta) comboCartas.getSelectedItem();
        if (seleccionada == null) {
            JOptionPane.showMessageDialog(this, "No hay carta seleccionada.");
            return;
        }

        try {
            if (seleccionada instanceof CartaPokemon) {
                int daño = Integer.parseInt(txtDaño.getText().trim());
                int cantEnergias = Integer.parseInt(txtCantEnergias.getText().trim());
                sistema.modificarCartaPokemon(seleccionada, daño, cantEnergias);

            } else if (seleccionada instanceof CartaItem) {
                int bonificacion = Integer.parseInt(txtBonificacion.getText().trim());
                sistema.modificarCartaItem(seleccionada, bonificacion);

            } else if (seleccionada instanceof CartaSupporter) {
                int efectosPorTurno = Integer.parseInt(txtEfectosPorTurno.getText().trim());
                sistema.modificarCartaSupporter(seleccionada, efectosPorTurno);

            } else if (seleccionada instanceof CartaEnergy) {
                String elemento = txtElemento.getText().trim();
                sistema.modificarCartaEnergy(seleccionada, elemento);
            }

            JOptionPane.showMessageDialog(this, "Carta modificada correctamente.");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Los atributos numéricos deben ser válidos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Refresca el combo de cartas existentes con el estado actual de la colección.
     */
    private void actualizarComboCartas() {
        comboCartas.removeAllItems();
        for (Carta c : sistema.listarCartas()) {
            comboCartas.addItem(c);
        }
    }
}