package service;

import dao.CuentaDAO;
import dao.TransaccionDAO;
import dao.DBUtil;
import model.Cuenta;
import model.Transaccion;
import java.sql.Connection;
import java.math.BigDecimal;

public class CuentaService {
    private CuentaDAO cuentaDAO = new CuentaDAO();
    private TransaccionDAO transDAO = new TransaccionDAO();

    // Depositar
    public void depositar(String numeroCuenta, BigDecimal monto, String descripcion) throws Exception {
        try (Connection conn = DBUtil.getConnection()) {
            conn.setAutoCommit(false);
            try {
                Cuenta c = cuentaDAO.obtenerPorNumero(numeroCuenta);
                if (c == null) throw new Exception("Cuenta no encontrada");
                BigDecimal nuevoSaldo = c.getSaldo().add(monto);
                cuentaDAO.actualizarSaldo(c.getCuentaId(), nuevoSaldo, conn);

                Transaccion t = new Transaccion();
                t.setCuentaId(c.getCuentaId());
                t.setTipo("DEPOSITO");
                t.setMonto(monto);
                t.setDescripcion(descripcion);
                t.setMoneda(c.getMoneda());
                transDAO.registrarTransaccion(t, conn);

                conn.commit();
            } catch (Exception ex) {
                conn.rollback();
                throw ex;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    // Retirar
    public void retirar(String numeroCuenta, BigDecimal monto, String descripcion) throws Exception {
        try (Connection conn = DBUtil.getConnection()) {
            conn.setAutoCommit(false);
            try {
                Cuenta c = cuentaDAO.obtenerPorNumero(numeroCuenta);
                if (c == null) throw new Exception("Cuenta no encontrada");
                if (c.getSaldo().compareTo(monto) < 0) throw new Exception("Saldo insuficiente");
                BigDecimal nuevoSaldo = c.getSaldo().subtract(monto);
                cuentaDAO.actualizarSaldo(c.getCuentaId(), nuevoSaldo, conn);

                Transaccion t = new Transaccion();
                t.setCuentaId(c.getCuentaId());
                t.setTipo("RETIRO");
                t.setMonto(monto);
                t.setDescripcion(descripcion);
                t.setMoneda(c.getMoneda());
                transDAO.registrarTransaccion(t, conn);

                conn.commit();
            } catch (Exception ex) {
                conn.rollback();
                throw ex;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    // Transferencia entre cuentas (atomica)
    public void transferir(String origenNumero, String destinoNumero, BigDecimal monto, String descripcion) throws Exception {
        try (Connection conn = DBUtil.getConnection()) {
            conn.setAutoCommit(false);
            try {
                Cuenta origen = cuentaDAO.obtenerPorNumero(origenNumero);
                Cuenta destino = cuentaDAO.obtenerPorNumero(destinoNumero);
                if (origen == null || destino == null) throw new Exception("Cuenta origen/destino no encontrada");
                if (origen.getSaldo().compareTo(monto) < 0) throw new Exception("Saldo insuficiente en cuenta origen");

                // Actualizar saldos
                cuentaDAO.actualizarSaldo(origen.getCuentaId(), origen.getSaldo().subtract(monto), conn);
                cuentaDAO.actualizarSaldo(destino.getCuentaId(), destino.getSaldo().add(monto), conn);

                // Registrar transacciones (dos registros)
                Transaccion t1 = new Transaccion();
                t1.setCuentaId(origen.getCuentaId());
                t1.setTipo("TRANSFERENCIA_SALIDA");
                t1.setMonto(monto);
                t1.setDescripcion(descripcion);
                t1.setOrigenCuenta(origenNumero);
                t1.setDestinoCuenta(destinoNumero);
                transDAO.registrarTransaccion(t1, conn);

                Transaccion t2 = new Transaccion();
                t2.setCuentaId(destino.getCuentaId());
                t2.setTipo("TRANSFERENCIA_ENTRADA");
                t2.setMonto(monto);
                t2.setDescripcion(descripcion);
                t2.setOrigenCuenta(origenNumero);
                t2.setDestinoCuenta(destinoNumero);
                transDAO.registrarTransaccion(t2, conn);

                conn.commit();
            } catch (Exception ex) {
                conn.rollback();
                throw ex;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }
}
