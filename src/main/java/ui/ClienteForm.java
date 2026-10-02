package ui;

import javax.swing.*;
import java.awt.*;

public class ClienteForm extends JPanel {
    private JTextField txtNombres, txtApellidos, txtDocumento, txtEmail, txtTelefono, txtDireccion;
    private JComboBox<String> cbTipoDocumento;
    private JButton btnGuardar, btnEditar, btnEliminar, btnBuscar;
    private JTable tablaClientes;

    public ClienteForm() {
        setLayout(new BorderLayout(10, 10));

        // 🔹 Panel de formulario (arriba)
        JPanel panelForm = new JPanel(new GridLayout(7, 2, 5, 5));

        panelForm.add(new JLabel("Nombres:"));
        txtNombres = new JTextField();
        panelForm.add(txtNombres);

        panelForm.add(new JLabel("Apellidos:"));
        txtApellidos = new JTextField();
        panelForm.add(txtApellidos);

        panelForm.add(new JLabel("Tipo Documento:"));
        cbTipoDocumento = new JComboBox<>(new String[]{"DNI", "CE", "PASAPORTE"});
        panelForm.add(cbTipoDocumento);

        panelForm.add(new JLabel("N° Documento:"));
        txtDocumento = new JTextField();
        panelForm.add(txtDocumento);

        panelForm.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panelForm.add(txtEmail);

        panelForm.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelForm.add(txtTelefono);

        panelForm.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panelForm.add(txtDireccion);

        // 🔹 Panel de botones (debajo del formulario)
        JPanel panelBotones = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnBuscar = new JButton("Buscar");

        panelBotones.add(btnGuardar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);

        // 🔹 Tabla en el centro
        tablaClientes = new JTable();
        JScrollPane scrollTabla = new JScrollPane(tablaClientes);

        // 📌 Agregar a la vista principal
        add(panelForm, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(scrollTabla, BorderLayout.SOUTH);
    }
}
