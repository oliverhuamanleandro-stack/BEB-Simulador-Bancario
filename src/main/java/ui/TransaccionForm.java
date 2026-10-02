package ui;

import javax.swing.*;
import java.awt.*;

public class TransaccionForm extends JPanel {
    private JComboBox<String> cbTipoTransaccion, cbMoneda;
    private JTextField txtCuentaOrigen, txtCuentaDestino, txtMonto, txtDescripcion;
    private JButton btnEjecutar, btnLimpiar, btnConsultar;
    private JTable tablaMovimientos;

    public TransaccionForm() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));

        // Tipo de transacción
        panel.add(new JLabel("Tipo de Transacción:"));
        cbTipoTransaccion = new JComboBox<>(new String[]{"Depósito", "Retiro", "Transferencia"});
        panel.add(cbTipoTransaccion);

        // Cuenta origen
        panel.add(new JLabel("Cuenta Origen:"));
        txtCuentaOrigen = new JTextField();
        panel.add(txtCuentaOrigen);

        // Cuenta destino
        panel.add(new JLabel("Cuenta Destino:"));
        txtCuentaDestino = new JTextField();
        panel.add(txtCuentaDestino);

        // Monto
        panel.add(new JLabel("Monto:"));
        txtMonto = new JTextField();
        panel.add(txtMonto);

        // Moneda
        panel.add(new JLabel("Moneda:"));
        cbMoneda = new JComboBox<>(new String[]{"PEN", "USD", "EUR"});
        panel.add(cbMoneda);

        // Descripción
        panel.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        panel.add(txtDescripcion);

        // Botones
        JPanel panelBotones = new JPanel();
        btnEjecutar = new JButton("Ejecutar");
        btnLimpiar = new JButton("Limpiar");
        btnConsultar = new JButton("Consultar Movimientos");

        panelBotones.add(btnEjecutar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnConsultar);

        // Tabla
        tablaMovimientos = new JTable();
        JScrollPane scrollTabla = new JScrollPane(tablaMovimientos);

        add(panel, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(scrollTabla, BorderLayout.SOUTH);

        // Eventos
        btnEjecutar.addActionListener(e -> ejecutarTransaccion());
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        btnConsultar.addActionListener(e -> consultarMovimientos());
    }

    private void ejecutarTransaccion() {
        String tipo = (String) cbTipoTransaccion.getSelectedItem();
        String cuentaOrigen = txtCuentaOrigen.getText();
        String cuentaDestino = txtCuentaDestino.getText();
        String monto = txtMonto.getText();
        String moneda = (String) cbMoneda.getSelectedItem();
        String descripcion = txtDescripcion.getText();

        JOptionPane.showMessageDialog(this,
            "Ejecutando " + tipo + " de " + monto + " " + moneda +
            "\nCuenta Origen: " + cuentaOrigen +
            (tipo.equals("Transferencia") ? "\nCuenta Destino: " + cuentaDestino : "") +
            "\nDescripción: " + descripcion
        );
    }

    private void limpiarFormulario() {
        txtCuentaOrigen.setText("");
        txtCuentaDestino.setText("");
        txtMonto.setText("");
        txtDescripcion.setText("");
        cbTipoTransaccion.setSelectedIndex(0);
        cbMoneda.setSelectedIndex(0);
    }

    private void consultarMovimientos() {
        JOptionPane.showMessageDialog(this, "Aquí se listarán los movimientos desde la BD.");
    }
}
