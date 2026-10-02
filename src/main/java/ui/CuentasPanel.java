package ui;

import javax.swing.*;
import java.awt.*;

public class CuentasPanel extends JPanel {
    private JTextField txtNumeroCuenta, txtDniCliente, txtSaldoInicial;
    private JComboBox<String> cbTipoCuenta, cbMoneda;
    private JButton btnCrear, btnBuscar, btnActualizar, btnEliminar;
    private JTable tablaCuentas;

    public CuentasPanel() {
        setLayout(new BorderLayout());

        // Panel de formulario
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 5, 5));

        panelForm.add(new JLabel("Número de Cuenta:"));
        txtNumeroCuenta = new JTextField();
        panelForm.add(txtNumeroCuenta);

        panelForm.add(new JLabel("DNI Cliente:"));
        txtDniCliente = new JTextField();
        panelForm.add(txtDniCliente);

        panelForm.add(new JLabel("Tipo de Cuenta:"));
        cbTipoCuenta = new JComboBox<>(new String[]{"Ahorros", "Corriente"});
        panelForm.add(cbTipoCuenta);

        panelForm.add(new JLabel("Saldo Inicial:"));
        txtSaldoInicial = new JTextField();
        panelForm.add(txtSaldoInicial);

        panelForm.add(new JLabel("Moneda:"));
        cbMoneda = new JComboBox<>(new String[]{"PEN", "USD", "EUR"});
        panelForm.add(cbMoneda);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        btnCrear = new JButton("Crear");
        btnBuscar = new JButton("Buscar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");

        panelBotones.add(btnCrear);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        // Tabla de cuentas
        tablaCuentas = new JTable();
        JScrollPane scrollTabla = new JScrollPane(tablaCuentas);

        // Agregar todo al panel principal
        add(panelForm, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(scrollTabla, BorderLayout.SOUTH);

        // Eventos básicos
        btnCrear.addActionListener(e -> crearCuenta());
        btnBuscar.addActionListener(e -> buscarCuenta());
        btnActualizar.addActionListener(e -> actualizarCuenta());
        btnEliminar.addActionListener(e -> eliminarCuenta());
    }

    private void crearCuenta() {
        JOptionPane.showMessageDialog(this,
                "Creando cuenta " + txtNumeroCuenta.getText() +
                " para cliente " + txtDniCliente.getText());
    }

    private void buscarCuenta() {
        JOptionPane.showMessageDialog(this,
                "Buscando cuenta " + txtNumeroCuenta.getText());
    }

    private void actualizarCuenta() {
        JOptionPane.showMessageDialog(this,
                "Actualizando cuenta " + txtNumeroCuenta.getText());
    }

    private void eliminarCuenta() {
        JOptionPane.showMessageDialog(this,
                "Eliminando cuenta " + txtNumeroCuenta.getText());
    }
}
