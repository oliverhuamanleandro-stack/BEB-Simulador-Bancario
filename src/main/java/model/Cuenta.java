package model;

import java.math.BigDecimal;

public class Cuenta {
    private long cuentaId;
    private long clienteId;
    private long productoId;
    private long asesorId;
    private String numeroCuenta;
    private String moneda;
    private BigDecimal saldo;
    private String estado;

    // Getters y Setters
    public long getCuentaId() { return cuentaId; }
    public void setCuentaId(long cuentaId) { this.cuentaId = cuentaId; }

    public long getClienteId() { return clienteId; }
    public void setClienteId(long clienteId) { this.clienteId = clienteId; }

    public long getProductoId() { return productoId; }
    public void setProductoId(long productoId) { this.productoId = productoId; }

    public long getAsesorId() { return asesorId; }
    public void setAsesorId(long asesorId) { this.asesorId = asesorId; }

    public String getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }

    public String getMoneda() { return moneda; }
    public void setMoneda(String moneda) { this.moneda = moneda; }

    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
