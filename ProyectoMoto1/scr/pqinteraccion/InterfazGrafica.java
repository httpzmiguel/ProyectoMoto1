package pqinteraccion;

import gestion.GestorParqueadero;
import gestion.MedioPago;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InterfazGrafica extends JFrame {
    private GestorParqueadero gestor;

    public InterfazGrafica() {
        gestor = new GestorParqueadero();

        setTitle("Sistema de Gestión de Parqueadero - Motos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Ingreso de Moto", crearPanelIngreso());
        tabbedPane.addTab("Salida de Moto", crearPanelSalida());
        tabbedPane.addTab("Reporte Diario", crearPanelReporte());

        add(tabbedPane);
    }

    private JPanel crearPanelIngreso() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblPlaca = new JLabel("Placa:");
        JTextField txtPlaca = new JTextField();

        JLabel lblMarca = new JLabel("Marca:");
        JTextField txtMarca = new JTextField();

        JLabel lblIdDuenio = new JLabel("ID Dueño:");
        JTextField txtIdDuenio = new JTextField();

        JButton btnRegistrar = new JButton("Registrar Ingreso");
        btnRegistrar.setBackground(new Color(113, 199, 245));

        btnRegistrar.addActionListener((ActionEvent e) -> {
            String placa = txtPlaca.getText().trim();
            String marca = txtMarca.getText().trim();
            String id = txtIdDuenio.getText().trim();

            if (placa.isEmpty() || marca.isEmpty() || id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor llene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String respuesta = gestor.registrarIngreso(placa, marca, id);
            JOptionPane.showMessageDialog(this, respuesta, "Registro", JOptionPane.INFORMATION_MESSAGE);

            txtPlaca.setText("");
            txtMarca.setText("");
            txtIdDuenio.setText("");
        });

        panel.add(lblPlaca);
        panel.add(txtPlaca);
        panel.add(lblMarca);
        panel.add(txtMarca);
        panel.add(lblIdDuenio);
        panel.add(txtIdDuenio);
        panel.add(new JLabel(""));
        panel.add(btnRegistrar);

        return panel;
    }

    private JPanel crearPanelSalida() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblPlaca = new JLabel("Placa de la Moto:");
        JTextField txtPlaca = new JTextField();

        JLabel lblPago = new JLabel("Medio de Pago:");
        String[] opcionesPago = {"Efectivo", "Nequi"};
        JComboBox<String> cmbPago = new JComboBox<>(opcionesPago);

        JButton btnSalida = new JButton("Registrar Salida");
        btnSalida.setBackground(new Color(255, 226, 125));

        btnSalida.addActionListener((ActionEvent e) -> {
            String placa = txtPlaca.getText().trim();
            if (placa.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese una placa válida", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            MedioPago medio = cmbPago.getSelectedIndex() == 0 ? MedioPago.EFECTIVO : MedioPago.NEQUI;
            String respuesta = gestor.registrarSalida(placa, medio);

            JOptionPane.showMessageDialog(this, respuesta, "Salida", JOptionPane.INFORMATION_MESSAGE);
            txtPlaca.setText("");
        });

        panel.add(lblPlaca);
        panel.add(txtPlaca);
        panel.add(lblPago);
        panel.add(cmbPago);
        panel.add(new JLabel(""));
        panel.add(btnSalida);

        return panel;
    }

    private JPanel crearPanelReporte() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextArea txtReporte = new JTextArea();
        txtReporte.setEditable(false);
        txtReporte.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(txtReporte);

        JButton btnActualizar = new JButton("Actualizar Reporte");

        btnActualizar.addActionListener((ActionEvent e) -> {
            String reporte = gestor.generarReporteDiario();
            txtReporte.setText(reporte);
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnActualizar, BorderLayout.SOUTH);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfazGrafica ventana = new InterfazGrafica();
            ventana.setVisible(true);
        });
    }
}