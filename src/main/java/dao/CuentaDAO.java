package dao;

import model.Cuenta;
import java.sql.*;
import java.math.BigDecimal;

public class CuentaDAO {

    public long crearCuenta(Cuenta c) throws SQLException {
        String sql = "INSERT INTO cuenta (cliente_id, producto_id, asesor_id, numero_cuenta, moneda, saldo, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, c.getClienteId());
            if (c.getProductoId() != 0) ps.setLong(2, c.getProductoId()); else ps.setNull(2, Types.BIGINT);
            if (c.getAsesorId() != 0) ps.setLong(3, c.getAsesorId()); else ps.setNull(3, Types.BIGINT);
            ps.setString(4, c.getNumeroCuenta());
            ps.setString(5, c.getMoneda());
            ps.setBigDecimal(6, c.getSaldo() == null ? BigDecimal.ZERO : c.getSaldo());
            ps.setString(7, c.getEstado() == null ? "ACTIVA" : c.getEstado());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    long id = rs.getLong(1);
                    c.setCuentaId(id);
                    return id;
                }
            }
        }
        return -1;
    }

    public Cuenta obtenerPorNumero(String numeroCuenta) throws SQLException {
        String sql = "SELECT cuenta_id, cliente_id, producto_id, asesor_id, numero_cuenta, moneda, saldo, estado FROM cuenta WHERE numero_cuenta = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, numeroCuenta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cuenta c = new Cuenta();
                    c.setCuentaId(rs.getLong("cuenta_id"));
                    c.setClienteId(rs.getLong("cliente_id"));
                    c.setProductoId(rs.getLong("producto_id"));
                    c.setAsesorId(rs.getLong("asesor_id"));
                    c.setNumeroCuenta(rs.getString("numero_cuenta"));
                    c.setMoneda(rs.getString("moneda"));
                    c.setSaldo(rs.getBigDecimal("saldo"));
                    c.setEstado(rs.getString("estado"));
                    return c;
                }
            }
        }
        return null;
    }

    public void actualizarSaldo(long cuentaId, java.math.BigDecimal nuevoSaldo, Connection conn) throws SQLException {
        String sql = "UPDATE cuenta SET saldo = ? WHERE cuenta_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, nuevoSaldo);
            ps.setLong(2, cuentaId);
            ps.executeUpdate();
        }
    }
}
