package model;

import java.math.BigDecimal;
import java.util.Date;

public class Transaccion {
    private long transaccionId;
    private long cuentaId;
    private String tipo;              // depósito, retiro, transferencia
    private BigDecimal monto;
    private String moneda;            // PEN, USD, EUR...
    private String descripcion;       // detalle de la transacción
    private String origenCuenta;      // nro de cuenta origen
    private String destinoCuenta;     // nro de cuenta destino
    private Date fecha;

    // Getters y Setters
    public long getTransaccionId() {
        return transaccionId;
    }

    public void setTransaccionId(long transaccionId) {
        this.transaccionId = transaccionId;
    }

    public long getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(long cuentaId) {
        this.cuentaId = cuentaId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getOrigenCuenta() {
        return origenCuenta;
    }

    public void setOrigenCuenta(String origenCuenta) {
        this.origenCuenta = origenCuenta;
    }

    public String getDestinoCuenta() {
        return destinoCuenta;
    }

    public void setDestinoCuenta(String destinoCuenta) {
        this.destinoCuenta = destinoCuenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
