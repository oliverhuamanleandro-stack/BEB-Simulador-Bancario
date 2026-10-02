package app;
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private JPanel contentPanel;
    private CardLayout cardLayout;

    public Main() {
        super("💳 BEB - Simulador Bancario");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
    }

    private void init() {
        // Layout principal
        setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(30, 144, 255));
        header.setPreferredSize(new Dimension(1000, 60));
        JLabel titulo = new JLabel("🏦 Sistema Bancario - BEB");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        header.add(titulo);
        add(header, BorderLayout.NORTH);

        // Sidebar (menú lateral)
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(5, 1, 5, 5));
        sidebar.setBackground(new Color(245, 245, 245));
        sidebar.setPreferredSize(new Dimension(200, 0));

        JButton btnClientes = new JButton("👤 Clientes");
        JButton btnCuentas = new JButton("💰 Cuentas");
        JButton btnTransacciones = new JButton("🔄 Transacciones");
        JButton btnSalir = new JButton("🚪 Salir");

        sidebar.add(btnClientes);
        sidebar.add(btnCuentas);
        sidebar.add(btnTransacciones);
        sidebar.add(btnSalir);

        add(sidebar, BorderLayout.WEST);

        // Panel central con CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        contentPanel.add(new ClienteForm(), "Clientes");
        contentPanel.add(new CuentasPanel(), "Cuentas");
        contentPanel.add(new TransaccionForm(), "Transacciones");

        add(contentPanel, BorderLayout.CENTER);

        // Acciones
        btnClientes.addActionListener(e -> cardLayout.show(contentPanel, "Clientes"));
        btnCuentas.addActionListener(e -> cardLayout.show(contentPanel, "Cuentas"));
        btnTransacciones.addActionListener(e -> cardLayout.show(contentPanel, "Transacciones"));
        btnSalir.addActionListener(e -> System.exit(0));
    }
}