package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel contentPanel;

    public MainFrame() {
        super("BEB - Simulador Bancario");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
    }

    private void init() {
        JMenuBar menuBar = new JMenuBar();
        JMenu mGest = new JMenu("Gestión");
        JMenuItem mClientes = new JMenuItem("Clientes");
        mClientes.addActionListener(e -> showPanel(new ClienteForm()));
        JMenuItem mCuentas = new JMenuItem("Cuentas");
        mCuentas.addActionListener(e -> showPanel(new CuentasPanel()));
        JMenuItem mTrans = new JMenuItem("Transacciones");
        mTrans.addActionListener(e -> new TransaccionForm().setVisible(true));

        mGest.add(mClientes);
        mGest.add(mCuentas);
        mGest.add(mTrans);
        menuBar.add(mGest);
        setJMenuBar(menuBar);

        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);
    }

    private void showPanel(JPanel p) {
        contentPanel.removeAll();
        contentPanel.add(p, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
