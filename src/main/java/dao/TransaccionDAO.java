package dao;

import model.Transaccion;
import java.sql.*;

public class TransaccionDAO {

    public long registrarTransaccion(Transaccion t, Connection conn) throws SQLException {
        String sql = "INSERT INTO transaccion (cuenta_id, tipo, monto, moneda, descripcion, origen_cuenta, destino_cuenta) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, t.getCuentaId());
            ps.setString(2, t.getTipo());
            ps.setBigDecimal(3, t.getMonto());
            ps.setString(4, t.getMoneda());
            ps.setString(5, t.getDescripcion());
            ps.setString(6, t.getOrigenCuenta());
            ps.setString(7, t.getDestinoCuenta());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getLong(1);
            }
        }
        return -1;
    }
}
