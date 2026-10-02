package dao;

import model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void insertar(Cliente c) throws SQLException {
        String sql = "INSERT INTO cliente (tipo_documento, documento, nombre, fecha_nacimiento, email, telefono) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getTipoDocumento());
            ps.setString(2, c.getDocumento());
            ps.setString(3, c.getNombre());
            if (c.getFechaNacimiento() != null) {
                ps.setDate(4, new java.sql.Date(c.getFechaNacimiento().getTime()));
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }
            ps.setString(5, c.getEmail());
            ps.setString(6, c.getTelefono());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) c.setClienteId(rs.getLong(1));
            }
        }
    }

    public List<Cliente> listarTodos() throws SQLException {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT cliente_id, tipo_documento, documento, nombre, fecha_nacimiento, email, telefono FROM cliente";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setClienteId(rs.getLong("cliente_id"));
                c.setTipoDocumento(rs.getString("tipo_documento"));
                c.setDocumento(rs.getString("documento"));
                c.setNombre(rs.getString("nombre"));
                java.sql.Date d = rs.getDate("fecha_nacimiento");
                if (d != null) c.setFechaNacimiento(new java.util.Date(d.getTime()));
                c.setEmail(rs.getString("email"));
                c.setTelefono(rs.getString("telefono"));
                lista.add(c);
            }
        }
        return lista;
    }
}

